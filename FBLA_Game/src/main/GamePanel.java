package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ai.PathFinder;
import cutscene.CutsceneManager;
import entity.Entity;
import entity.Player;
import entity.Player2;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	// Screen settings
    final int originalTileSize = 32; // 32x32 tile
    final int scale = 2;
    public boolean colorblindFilter = false;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 14;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol; // 896 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 640 pixels

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0; // Sets the current map. make sure this is 0 when finished developing

    public final int WorldWidth = tileSize * maxWorldCol;
    public final int WorldHeight = tileSize * maxWorldRow;

    // FPS
    public int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this, tileM);
    public PathFinder pFinder = new PathFinder(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread;

    // Creating entities and objects
    public Player player = new Player(this,keyH);
    public Player2 player2 = new Player2(this,keyH);
    public Entity obj[][] = new Entity[maxMap][10];
    public Entity npc[][]= new Entity[maxMap][10];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    // Double-buffering
    private BufferedImage offScreenImage; 
    private Graphics2D offScreenGraphics;

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int cutsceneState = 5;
    public final int optionsState = 6;
    public final int tradeState = 7;
    public final int instructionsState = 8;
    public final int choiceState = 9;
    public final int battleState = 10;
    public final int gameOverState = 11;
    
    // Fade to Black Transition
    public boolean isFading = false;
    public int fadeAlpha = 0; 
    public boolean fadeIn = false;
    public Runnable onFadeComplete; 
    public float fadeAlphaEvents = 0f; // Controls the opacity of the fade (0 = transparent, 1 = fully black)
    public boolean isFadingToBlack = false; // Indicates if the screen is fading to black
    public boolean isFadingToNormal = false;
    
    // Full Screen
    public boolean isFullScreen = false;
    public GraphicsDevice gd;
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        
        offScreenImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        offScreenGraphics = (Graphics2D) offScreenImage.getGraphics();
    }
    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
        aSetter.setNPC();
        gameState = titleState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        if (gameState == playState) {
            player.update();
            
            if (currentMap == 7) { // Update Player2 only on specific map
                player2.update();
            }

            for (int i = 0; i < npc[currentMap].length; i++) { 
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }	
        }

        if (gameState == pauseState) {
            // nothing 
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ui.drawFadeOverlay(g2);
        
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        offScreenGraphics.setColor(Color.black);
        offScreenGraphics.fillRect(0, 0, screenWidth, screenHeight);

        offScreenGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        offScreenGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        
        if (currentMap == 7) {
            drawPuzzleMap(g2); // Draw entire map once
            
            // Draw players at their actual world positions
            player.draw(g2, player.worldX, player.worldY);
            player2.draw(g2, player2.worldX, player2.worldY);
            
            // Draw NPCs and objects
            for (Entity npc : npc[currentMap]) {
                if (npc != null) npc.draw(g2);
            }
            for (Entity obj : obj[currentMap]) {
                if (obj != null) obj.draw(g2);
            }
       
        } else {
        	
        	// Debugging
        	long drawStart = 0;
        	if (keyH.checkDrawTime == true) {
        		drawStart = System.nanoTime();
        	}
    	
        if (eHandler.isCutscenePlaying && eHandler.cutsceneGif != null) {
            // Convert the Image to a BufferedImage
            Image cutsceneImage = eHandler.cutsceneGif.getImage();
            BufferedImage bufferedImage = toBufferedImage(cutsceneImage);

            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();
            double imageAspectRatio = (double) imageWidth / imageHeight;
            double screenAspectRatio = (double) screenWidth / screenHeight;

            int drawWidth, drawHeight;
            int drawX, drawY;

            if (imageAspectRatio > screenAspectRatio) {
                drawWidth = screenWidth;
                drawHeight = (int) (screenWidth / imageAspectRatio);
                drawX = 0;
                drawY = (screenHeight - drawHeight) / 2;
            } else {
                drawHeight = screenHeight;
                drawWidth = (int) (screenHeight * imageAspectRatio);
                drawX = (screenWidth - drawWidth) / 2;
                drawY = 0;
            }

            offScreenGraphics.setColor(Color.black);
            offScreenGraphics.fillRect(0, 0, screenWidth, screenHeight);

            offScreenGraphics.drawImage(bufferedImage, drawX, drawY, drawWidth, drawHeight, null);
        } else {
            if (gameState == titleState) {
                ui.draw(offScreenGraphics);
            } else {
                // Draw tiles
                tileM.draw(offScreenGraphics);

                // Add entities
                entityList.add(player);
                for (int i = 0; i < npc[currentMap].length; i++) {
                    if (npc[currentMap][i] != null) {
                        entityList.add(npc[currentMap][i]);
                    }
                }
                for (int i = 0; i < obj[currentMap].length; i++) {
                    if (obj[currentMap][i] != null) {
                        entityList.add(obj[currentMap][i]);
                    }
                }

                // Sort and draw entities
                Collections.sort(entityList, Comparator.comparingInt(e -> e.worldY));
                for (Entity element : entityList) {
                    element.draw(offScreenGraphics);
                }
                entityList.clear();
                
                // Draw UI
                ui.draw(offScreenGraphics);
                
                // Cutscene
                csManager.draw(g2);
            }
        }
        
        // debug - delete when finished
        if (keyH.checkDrawTime == true) {
        	long drawEnd = System.nanoTime();
        	long passed = drawEnd - drawStart;
        	
        	g2.setFont(new Font("Arial", Font.PLAIN,20));
        	g2.setColor(Color.white);
        	int x = 10;
        	int y = 400;
        	int lineHeight = 20;
        	
        	g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
        	g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
        	g2.drawString("Col" + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
        	g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
        	System.out.println("Draw Time: " + passed);
        }
        if (isFading == true) {
            offScreenGraphics.setColor(new Color(0, 0, 0, fadeAlpha)); 
            offScreenGraphics.fillRect(0, 0, screenWidth, screenHeight);
        }

        if (isFullScreen == true) {
            int scaledWidth = (int)(screenWidth * 1.4);
            int scaledHeight = (int)(screenHeight * 1.4);

            int screenCenterX = ((gd.getDisplayMode().getWidth() - scaledWidth) / 2)-200;
            int screenCenterY = ((gd.getDisplayMode().getHeight() - scaledHeight) / 2)-100;

            g2.drawImage(offScreenImage, screenCenterX, screenCenterY, scaledWidth, scaledHeight, null);
        } else {
            g2.drawImage(offScreenImage, 0, 0, screenWidth, screenHeight, null);
        }

        g2.dispose();
        }
        
    }
    public BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bufferedImage = new BufferedImage(
            img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }
    public void toggleFullScreen() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            if (isFullScreen) {
            	
                frame.dispose();
                frame.setUndecorated(false);
                frame.setVisible(true);
                gd.setFullScreenWindow(null);
                isFullScreen = false;
            } else {

            	frame.dispose();
                frame.setUndecorated(true);
                frame.setVisible(true);
                gd.setFullScreenWindow(frame);
                isFullScreen = true;
            }
        }
    }
    public void playMusic(int i) {

    	sound.setFile(i);
    	sound.play();
    	sound.loop();
    }
    public void stopMusic() {

    	sound.stop();
    }
    public void playSE(int i) {

    	sound.setFile(i);
    	sound.play();
    }

    public void startRoomTransition(Runnable onComplete) {

        isFading = true;
        fadeIn = true;
        fadeAlpha = 0;
        onFadeComplete = onComplete;

        Timer timer = new Timer(30, e -> {
            if (fadeIn && fadeAlpha < 255) {
            	fadeAlpha += 15;
                repaint();
            } else if (fadeIn && fadeAlpha >= 255) {
                fadeIn = false;
                onFadeComplete.run();
            } else if (!fadeIn && fadeAlpha > 0) {
                fadeAlpha -= 15;
                repaint();
            } else if (!fadeIn && fadeAlpha <= 0) {
                isFading = false;
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();
    }
    public void removeTempEntity() {
    	
    	for (int mapNum  = 0; mapNum < maxMap; mapNum++ ) {
    		
    		for(int i = 0; i <  obj[1].length; i++) {
    			if(obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
    				obj[mapNum][i] = null;
    			}
    		}
    	}
    }
    public void drawPuzzleMap(Graphics2D g2) {

    	for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                int tileNum = tileM.mapTileNum[currentMap][col][row];
                int x = col * tileSize;
                int y = row * tileSize;
                g2.drawImage(tileM.tile[tileNum].image, x, y, tileSize, tileSize, null);
            }
        }
    }
}