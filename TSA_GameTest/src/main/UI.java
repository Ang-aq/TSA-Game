package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_coin;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage coin;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	private BufferedImage titleBackgroundImage;
	public int commandNum = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	public Queue<String> dialogueQueue = new LinkedList<>();
	public Entity npc;
	int charIndex = 0;
	String combinedText = "";
    public int currentObjective;
    boolean inBuyMenu = false;
	private Runnable onDialogueComplete;
    
	// Quests/Objectives
    public ArrayList<Objective> objectives = new ArrayList<>();
    
    // Questions
    public boolean choiceActive = false; // Is the choice menu active?
    public String question = "";
    public String[] choices = new String[2];
    public int selectedChoice = 0; // Tracks which option is selected

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("' RonySiswadi Architect 5", Font.BOLD, 40);
		arial_80B = new Font("' RonySiswadi Architect 5", Font.BOLD, 80);

		objectives.add(new Objective("Press WASD\nto Move"));
		objectives.add(new Objective("Go to the kitchen"));
		objectives.add(new Objective("Talk to MOM"));
		objectives.add(new Objective("Leave the house"));
		objectives.add(new Objective("Go to the wand \nstore"));
		objectives.add(new Objective("Talk to the \nhomeless man"));
		objectives.add(new Objective("Enter the wand\nstore"));
		objectives.add(new Objective("Talk to the \nshop clerk"));
		objectives.add(new Objective("Leave the store"));
		objectives.add(new Objective("Take a seat"));
		objectives.add(new Objective("Walk to class"));
		objectives.add(new Objective("Leave class"));
		objectives.add(new Objective("See what they're\ntalking about"));
		objectives.add(new Objective("Go to the \ncourtyard"));
		
		showObjective(0);
		
		// Creates a HUD object
		Entity ShopCoin = new OBJ_coin(gp);
		coin = ShopCoin.down1;

	 }
	// Draws the UI Screens depending on the Game State
	public void draw(Graphics2D g2) {

		this.g2 = g2;
        drawFadeOverlay(g2);

		g2.setFont(arial_40);
		g2.setColor(Color.white);

		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if(gp.gameState == gp.instructionsState) {
			drawInstructionsScreen();
		}
		if(gp.gameState == gp.playState) {
	        drawObjectives(g2);
		}
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		if(gp.gameState == gp.choiceState) {
			drawChoiceScreen();
		}
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}

	}
    public void setOnDialogueComplete(Runnable onDialogueComplete) {
        this.onDialogueComplete = onDialogueComplete;
    }
    public void completeDialogue() {
        if (onDialogueComplete != null) {
            onDialogueComplete.run(); // Execute the callback
            onDialogueComplete = null; // Reset the callback
        }
    }
	// Draws the title screen
	public void drawTitleScreen() {

		// Draw the background image
		try {
			titleBackgroundImage = ImageIO.read(getClass().getResourceAsStream("/player/titlescreen.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		g2.drawImage(titleBackgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

	    // Draws the game title
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F)); // Font size 
	    String text = "PRESS F TO START";  // Sets the title
	    int x = getXforCenteredText(text); // 
	    int y = gp.tileSize * 3;

	    // Text Shadow
	    g2.setColor(Color.black);
	    g2.drawString(text, x + 5, y + 5);

	    // Title text color
	    g2.setColor(Color.black);
	    g2.drawString(text, x, y);

	    // Menu Options
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 49F));

	    // Start Game option
	    text = "Start Game";
	    x = getXforCenteredText (text);
	    y += gp.tileSize*4;
	    g2.drawString(text, x, y);
	    if(commandNum == 0) {
	    	g2.drawString(">", x-gp.tileSize, y);

	    }
	    // Text for game instructions on the Title Screen opening instructions is handled in Key Handler
	    text = "Instructions";
	    x = getXforCenteredText (text);
	    y += gp.tileSize;
	    g2.drawString(text, x, y);
	    if(commandNum == 1) {
	    	g2.drawString(">", x-gp.tileSize, y); 
	    }
	    
	    // Text for quit game
	    text = "Quit Game";
	    x = getXforCenteredText (text);
	    y += gp.tileSize;
	    g2.drawString(text, x, y);
	    if(commandNum == 2) {
	    	g2.drawString(">", x-gp.tileSize, y);

	    }
	}
	// Draws the Pause Screen. Simply text that says "PAUSED" in the center of the screen.
	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		// Check if npc or dialogues are null
	    if (npc == null || npc.dialogues == null || npc.dialogues[npc.dialogueSet] == null) {
	        return; // Exit if there's no valid dialogue to display
	    }

	    // Dialogue Window
	    int x = gp.tileSize * 2;
	    int y = gp.tileSize * 6 + 40;
	    int width = gp.screenWidth - (gp.tileSize * 4);
	    int height = gp.tileSize * 3;
	    drawSubWindow(x, y, width, height);

	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
	    x += gp.tileSize;
	    y += gp.tileSize;

	    // Check if the current dialogue line exists
	    if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
	        char[] characters = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

	        if (charIndex < characters.length) {
	            String s = String.valueOf(characters[charIndex]);
	            combinedText = combinedText + s;
	            currentDialogue = combinedText;
	            charIndex++;
	        }

	        if (gp.keyH.fPressed) {
	            charIndex = 0;
	            combinedText = "";

	            if (gp.gameState == gp.dialogueState) {
	                npc.dialogueIndex++;
	                gp.keyH.fPressed = false;
	            }
	        }
	    } else {
	        // Dialogue set is complete
	        npc.dialogueIndex = 0;

	        if (gp.gameState == gp.dialogueState) {
	            gp.gameState = gp.playState;
	            completeDialogue(); // Trigger the callback
	        }
	    }

	    for (String line : currentDialogue.split("\n")) {
	        g2.drawString(line, x, y);
	        y += 40;
	    }

	    // Adds "Press F to continue" at the bottom of the dialogue box
	    if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null &&
	        charIndex >= npc.dialogues[npc.dialogueSet][npc.dialogueIndex].length()) {
	        String hintText = "Press F to continue";
	        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18f));
	        int hintX = gp.screenWidth - gp.tileSize * 5 + 3;
	        int hintY = gp.tileSize * 9 + 25;
	        g2.setColor(Color.LIGHT_GRAY);
	        g2.drawString(hintText, hintX, hintY);
	    }
	}
	//Draws the choice box for the player to respond to questions 
	public void drawChoiceScreen() {

	    int frameX = gp.tileSize * 4;
	    int frameY = gp.tileSize * 7;
	    int frameWidth = gp.tileSize * 6;
	    int frameHeight = gp.tileSize * 2;
	    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

	    // Draw the title
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
	    g2.setColor(Color.YELLOW);
	    g2.drawString("Pick an Option", frameX + 20, frameY + 30);

	    // Draw the choices
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22f));
	    int optionX = frameX + 40;
	    int optionY = frameY + 60;

	    for (int i = 0; i < choices.length; i++) {
	        if (i == selectedChoice) {
	            g2.setColor(Color.GREEN);
	        } else {
	            g2.setColor(Color.WHITE);
	        }
	        g2.drawString(choices[i], optionX, optionY);
	        optionY += 40;
	    }
	}
	public void drawInstructionsScreen() {
	    // Clears the background with black
	    g2.setColor(Color.black);
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    // Sets the title
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
	    String text = "INSTRUCTIONS";
	    int x = getXforCenteredText(text);
	    int y = gp.tileSize * 2;
	    g2.setColor(Color.white);
	    g2.drawString(text, x, y);

	    // Instructions Text
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
	    String[] instructions = {
	        "1. Use WASD to Move",
	        "2. Press F to Select, Interact, or Continue.",
	        "3. Press E to Open Inventory",
	        "4. Press ESC to Open Options Menu",
	        "5. Press P to Pause",
	        "Press F to continue"

	    };
	    x = gp.tileSize * 2;
	    y += gp.tileSize;

	    for (String line : instructions) {
	        g2.drawString(line, x, y);
	        y += gp.tileSize;
	    }

	    // Back button
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
	    text = "Back";
	    x = getXforCenteredText(text);
	    y += gp.tileSize * 2;
	    g2.drawString(text, x, y);
	    if (commandNum == 0) {
	        g2.drawString(">", x - gp.tileSize, y);
	    }
	}
	public void drawObjectives(Graphics2D g2) {
	    int padding = gp.tileSize / 4;
	    int windowX = gp.tileSize / 2;
	    int windowY = gp.tileSize / 2; 	
	    int windowWidth = gp.tileSize * 3;
	    int windowHeight = gp.tileSize * 2;

	    // Draw the sub window
	    drawSubWindow(windowX, windowY, windowWidth, windowHeight);

	    // Draw the title "Objectives:"
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
	    g2.setColor(Color.WHITE);
	    String title = "Objective:";
	    int titleX = windowX + padding;
	    int titleY = windowY + gp.tileSize - 32;
	    g2.drawString(title, titleX, titleY);

	    // Draw each objective within the sub window
	    int textX = windowX + padding;
	    int textY = titleY + gp.tileSize / 2; 
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22f));

	    for (Objective obj : objectives) {
	        if (obj.visible) {
	            if (obj.opacity < 1f && obj.visible) {
	                obj.opacity += 0.05f; 
	            } else if (!obj.visible && obj.opacity > 0f) {
	                obj.opacity -= 0.05f;
	            }

	            if (obj.opacity <= 0f) continue;

	            // Sub window opacity
	            float clampedOpacity = Math.max(0.0f, Math.min(obj.opacity, 1.0f));
	            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, clampedOpacity));

	            // Split the text by "\n" and draw each line
	            String[] lines = obj.text.split("\n");
	            for (String line : lines) {
	                g2.drawString(line, textX, textY);
	                textY += gp.tileSize / 4+6; // Adjusts the line spacing
	            }

	            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	            textY += gp.tileSize / 4;
	        }
	    }
	}
	public void showObjective(int index) {
	    if (index >= 0 && index < objectives.size()) {
	        Objective obj = objectives.get(index);
	        obj.visible = true;
	        obj.opacity = 0f; // Start fade-in
	        currentObjective = index;
	    } else {
	        System.out.println("Invalid objective index: " + index);
	    }
	}
	public void hideObjective(int index) {
		if (index >= 0 && index < objectives.size()) {
	        Objective obj = objectives.get(index);
	        obj.visible = false;
	        obj.opacity = 255f; // Start fade-out
	    } else {
	        System.out.println("Invalid objective index: " + index);
	    }
	}
	public void drawCharacterScreen() {

		// Frame
		final int frameX = gp.tileSize/2;
		final int frameY = gp.tileSize/2;
		final int frameWidth = gp.tileSize*6;
		final int frameHeight = gp.tileSize;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		int textX = frameX + 20;
		int textY = frameY + 41;
		final int lineHeight = 32;

		//Names
		g2.drawString("Name", textX, textY);
		textY += lineHeight;

		// Values
		int tailX = (frameX + frameWidth) - 30;

		// Reset textY
		textY = frameY + 41;
		String value;

		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString("Coins:", textX, textY);
		g2.drawString(value, textX+100, textY);
	}
	public void drawInventory(Entity entity, boolean cursor) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if (entity == gp.player) {
			 frameX = gp.tileSize*7;
			 frameY = gp.tileSize/2;
			 frameWidth = gp.tileSize*6;
			 frameHeight = gp.tileSize*5;
			 slotCol = playerSlotCol;
			 slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize/2;
			frameY = gp.tileSize/2;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		// Frame
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);

		// Inventory slots
		final int slotXstart = frameX + 32;
		final int slotYstart = frameY + 32;
		int slotX = slotXstart;
		int slotY = slotYstart;

		// Draw Entity Items
		for(int i = 0; i < entity.inventory.size(); i++) {

			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

			slotX += gp.tileSize;

			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
		}

		// Cursor
		if(cursor == true) {
			int cursorX = slotXstart + (gp.tileSize * slotCol);
			int cursorY = slotYstart + (gp.tileSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			// Draw Cursor
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

			// Item Descriptions
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize*3;
			
			// Draw Descriptions
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28f));

			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

			if(itemIndex < entity.inventory.size()) {
			drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
			for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
				}
			}
		}
	}
	public void drawOptionsScreen() {

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		// Sub Window
		int frameX = gp.tileSize*4;
		int frameY = gp.tileSize-32;
		int frameWidth = gp.tileSize*7;
		int frameHeight= gp.tileSize*9;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);

		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: break;
		case 2: break;
		}
	}
	public void options_top(int frameX, int frameY) {
	    int textX;
	    int textY;

	    // Title
	    String text = "OPTIONS";
	    textX = getXforCenteredText(text);
	    textY = frameY + gp.tileSize;
	    g2.drawString(text, textX + 32, textY);

	    // Colorblind Assistance On/Off
	    textX = frameX + gp.tileSize;
	    textY += gp.tileSize;
	    g2.drawString("Colorblind Filter", textX, textY);
	    if (commandNum == 0) {
	        g2.drawString(">", textX - 25, textY);
	    }

	    // Full-Screen Option
	    textY += gp.tileSize;
	    g2.drawString("Full Screen: " + (gp.isFullScreen ? "On" : "Off"), textX, textY);
	    if (commandNum == 1) {
	        g2.drawString(">", textX - 25, textY);
	    }

	    // Music
	    textY += gp.tileSize;
	    g2.drawString("Music", textX, textY);
	    if (commandNum == 2) {
	        g2.drawString(">", textX - 25, textY);
	    }

	    // Quit Game
	    textY += gp.tileSize;
	    g2.drawString("Quit Game", textX, textY);
	    if (commandNum == 3) {
	        g2.drawString(">", textX - 25, textY);
	    }

	    // Back
	    textY += gp.tileSize * 2;
	    g2.drawString("Back", textX, textY);
	    if (commandNum == 4) {
	        g2.drawString(">", textX - 25, textY);
	    }
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		}
		gp.keyH.fPressed = false;
	}
	public void trade_select() {
		
		npc.dialogueSet = 0;
	    drawDialogueScreen();
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

	    // Draw Window
	    int x = gp.tileSize * 10 + 20;
	    int y = gp.tileSize * 4 - 25;
	    int width = gp.tileSize * 3;
	    int height = gp.tileSize * 3;
	    drawSubWindow(x, y, width, height);

	    // Draw Text
	    x += gp.tileSize-15;
	    y += gp.tileSize;
	    g2.drawString("Buy Items", x, y);
	    if (commandNum == 0) {
	        g2.drawString(">", x - 24, y);
	        if (gp.keyH.fPressed) { 
	            subState = 1; 
	            gp.keyH.fPressed = false;
	        }
	    }
	    
	    y += gp.tileSize;

	    g2.drawString("Leave", x, y);
	    if (commandNum == 1) {
	        g2.drawString(">", x - 24, y);
	        if (gp.keyH.fPressed) {
	            commandNum = 0;
	            npc.startDialogue(npc, 2); // Start the leaving dialogue
	            gp.keyH.fPressed = false;
	        }
	    }
	}
	public void trade_buy() {
	    // Draw Player Inventory
	    drawInventory(gp.player, false);
	    
	    // Draw Clerk Inventory
	    drawInventory(npc, true);
	    
	    // Draw Hint Window
	    int x = gp.tileSize * 2 + 20;
	    int y = gp.tileSize * 8 + 30;
	    int width = gp.tileSize * 4;
	    int height = gp.tileSize;
	    drawSubWindow(x, y, width, height);
	    g2.drawString("Press ESC to leave", x + 17, y + 40);
	    
	    // Draw Player Coin Window
	    x = gp.tileSize * 7;
	    y = (int)(gp.tileSize * 5.5);
	    width = gp.tileSize * 2;
	    height = gp.tileSize;
	    drawSubWindow(x, y, width, height);
	    g2.drawString("Coins: " + gp.player.coin, x + 10, y + 40);
	    
	    // Draw Price Window
	    int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
	    
	    if (itemIndex < npc.inventory.size()) {
	        x = (int)(gp.tileSize * 3);
	        y = (int)(gp.tileSize * 5);
	        width = (int)(gp.tileSize * 3);
	        height = gp.tileSize;
	        drawSubWindow(x, y, width, height);
	        g2.drawImage(coin, x + 10, y + 15, 32, 32, null);
	        
	        int price = npc.inventory.get(itemIndex).price;
	        String text = "Cost: " + price +"$";
	        x = getXforAlignToRightText(text, gp.tileSize * 8 - 60);
	        g2.drawString(text, x, y + 40);
	        
	        // Buy Item
	        if (gp.keyH.fPressed) {
	            if (npc.inventory.get(itemIndex).price > gp.player.coin) {
	                npc.startDialogue(npc, 1);
	                subState = 0;
	            } else {
	                gp.player.coin -= npc.inventory.get(itemIndex).price;
	                gp.player.inventory.add(npc.inventory.get(itemIndex));
	            }
	            gp.keyH.fPressed = false;
	        }
	    }
	 
	    if (gp.keyH.escPressed) {
	        subState = 0; 
	        inBuyMenu = false; 
	        gp.keyH.escPressed = false; 
	    }
	}
	public void drawGameOverScreen() {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Press R to Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
    }
	public void drawFadeOverlay(Graphics2D g2) {
	    if (gp.fadeAlphaEvents > 0f) {
	        // Set the color to black with the current fadeAlpha
	        g2.setColor(new Color(0, 0, 0, gp.fadeAlphaEvents));
	        // Fill the entire screen with the black overlay
	        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
	    }
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int width, int height) {

		Color c = new Color(0, 0, 0, 200); // final value will adjust the opacity.
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}

	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}

	public int getXforAlignToRightText(String text, int tailX) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length-100;
		return x;
	}	
}