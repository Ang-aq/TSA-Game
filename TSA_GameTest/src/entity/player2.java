package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player2 extends Player {

    public Player2(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 5;
        direction = "down";
    }
    public void getPlayerImage() {

		up1 = setup("/wally/WallyBack");
		up2 = setup("/wally/WallyBack1");
		up3 = setup("/wally/WallyBack2");
		down1 = setup("/wally/NPC_Ron");
		down2 = setup("/wally/WallyFront1");
		down3 = setup("/wally/WallyFront2");
		left1 = setup("/wally/WallyLeft2");
		left2 = setup("/wally/WallyLeft1");
		left3 = setup("/wally/WallyLeft2");
		right1 = setup("/wally/WallyRight");
		right2 = setup("/wally/WallyRight1");
		right3 = setup("/wally/WallyRight");
	}

    public void update() {
    	
        if (keyH.upArrowPressed || keyH.downArrowPressed || keyH.leftArrowPressed || keyH.rightArrowPressed) {
        	
        	// Reset collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
			gp.eHandler.checkEvent();
                   	
        	if (keyH.upArrowPressed) {direction = "up";}
            else if (keyH.downArrowPressed) {direction = "down";}
            else if (keyH.leftArrowPressed) {direction = "left";}
            else if (keyH.rightArrowPressed) {direction = "right";}

            checkCollision();
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
         // Clamp player position within map bounds
            worldX = Math.max(0, Math.min(worldX, gp.maxWorldCol * gp.tileSize - gp.tileSize));
            worldY = Math.max(0, Math.min(worldY, gp.maxWorldRow * gp.tileSize - gp.tileSize));

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum % 4) + 1;
                spriteCounter = 0;
            }
        }
    }
