package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_key;
import object.OBJ_letter;

public class Player extends Entity{

	KeyHandler keyH;

	public int screenX;
	public int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);

		this.keyH = keyH;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		solidArea = new Rectangle(14, 40, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
		setItems();
		direction = "down";
	}
	public void setDefaultValues() {

		worldX = (int) (gp.tileSize * 20.5); // gp.tileSize * 12;
		worldY = gp.tileSize * 11; // gp.tileSize * 16;
		speed = 5;

		//player Status
		coin = 30;
		name = "Luke";
		}
	public void setItems() {

		inventory.add(new OBJ_letter(gp));
		inventory.add(new OBJ_key(gp));
	}
	public void getPlayerImage() {

		up1 = setup("/player/MCBack1");
		up2 = setup("/player/MCBack2");
		up3 = setup("/player/MCBack3");
		down1 = setup("/player/MCWalking1");
		down2 = setup("/player/MCWalking2");
		down3 = setup("/player/MCWalking3");
		left1 = setup("/player/MCLeft2");
		left2 = setup("/player/MCLeft1");
		left3 = setup("/player/MCLeft3");
		right1 = setup("/player/MCRight3");
		right2 = setup("/player/MCRight2");
		right3 = setup("/player/MCRight1");
	}
	@Override
	public void update() {

		if(keyH.upPressed || keyH.downPressed
			|| keyH.leftPressed || keyH.rightPressed || keyH.fPressed) {
			if(keyH.upPressed) {
				direction = "up";
			}
			else if(keyH.downPressed) {
				direction = "down";
			}
			else if(keyH.leftPressed) {
				direction = "left";
			}
			else if(keyH.rightPressed) {
				direction = "right";
			}

			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this); // Removing this removes tile collisions

			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// Check NPC Collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// Check Event
			gp.eHandler.checkEvent();

			// if collision false, player can move.
			if(!collisionOn && !keyH.fPressed) {

				switch(direction) {

				case"up": worldY -= speed; break;
				case"down": worldY += speed; break;
				case"left": worldX -= speed; break;
				case"right": worldX += speed; break;
				}
			}
			gp.keyH.fPressed = false;
			
			// Clamp player position within map bounds
	        worldX = Math.max(0, Math.min(worldX, gp.maxWorldCol * gp.tileSize - gp.tileSize));
	        worldY = Math.max(0, Math.min(worldY, gp.maxWorldRow * gp.tileSize - gp.tileSize));

			spriteCounter++;
			if(spriteCounter>11) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	public void pickUpObject(int i) {

		if(i != 999) {


		}
	}
	public void interactNPC(int i) {

		if(i != 999) {

			if(gp.keyH.fPressed) {
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}
	@Override
	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up1;
			}
			if(spriteNum == 4) {
				image = up3;
			}

			break;

		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down1;
			}
			if(spriteNum == 4) {
				image = down3;
			}
			break;

		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right1;
			}
			if(spriteNum == 4) {
				image = right3;
			}
			break;

		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left1;
			}
			if(spriteNum == 4) {
				image = left3;
			}
			break;
		}		
		if(drawing == true) {
			g2.drawImage(image, screenX, screenY, null);
		}
	}
	public void draw(Graphics2D g2, int screenX, int screenY) {
		BufferedImage image = null;
		screenX = worldX;
	    screenY = worldY;
	    
	    if (gp.currentMap != 7) { 
	        // Default camera-following behavior
	        screenX = worldX - gp.player.worldX + gp.player.screenX;
	        screenY = worldY - gp.player.worldY + gp.player.screenY;
	    }
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up1;
			}
			if(spriteNum == 4) {
				image = up3;
			}

			break;

		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down1;
			}
			if(spriteNum == 4) {
				image = down3;
			}
			break;

		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right1;
			}
			if(spriteNum == 4) {
				image = right3;
			}
			break;

		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left1;
			}
			if(spriteNum == 4) {
				image = left3;
			}
			break;
		}		
		
		if(drawing == true) {
			g2.drawImage(image, screenX, screenY, null);
		}
	}
}