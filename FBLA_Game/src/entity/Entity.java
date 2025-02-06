package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX, worldY;
	public int speed;

	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public String direction = "down";

	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle (0, 0, 64, 64);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLookCounter = 0;
	public String dialogues[][] = new String[50][50];
	public int dialogueIndex = 0;
	public int dialogueSet = 0;
	public BufferedImage image, image2, image3;
	public boolean collision = false;
	public boolean sleep = false;
	public boolean temp = false;
    public boolean drawing = true;
	public boolean transformed = false;


	// Character Attributes
	public String name;
	public int coin;
	public Entity currentWeapon;
	public boolean onPath = false; 
	
	// Item Attributes
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int price;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void setAction() {}
	public void speak() {

	}
	public void facePlayer() {

//		switch(gp.player.direction) {
//			case "up": direction = "down"; break;
//			case "down": direction = "up"; break;
//			case "left": direction = "right"; break;
//			case "right": direction = "left"; break;
//		}
	}
	public void startDialogue(Entity entity, int setNum, Runnable onComplete) {
	    gp.gameState = gp.dialogueState;
	    entity.dialogueSet = setNum;
	    entity.dialogueIndex = 0;
	    gp.ui.setOnDialogueComplete(onComplete);
	    gp.ui.npc = entity;
	}
	public void startDialogue(Entity entity, int setNum) {
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
	}
	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
	}
	public void update() {
		
		if (sleep == false) {
			setAction();
			checkCollision();
		
			// if collision false, player can move.
			if(collisionOn == false) {
				
				switch(direction) {

				case"up": worldY -= speed; break;
				case"down": worldY += speed; break;
				case"left": worldX -= speed; break;
				case"right": worldX += speed; break;
				}	
			}

			spriteCounter++;
			if(spriteCounter>34) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}

		return image;
	}
	public void draw (Graphics2D g2) {
		
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
				
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
				
			case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;
				
			case "left":
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		if(gp.pFinder.search() == true) {
			
			// next world x and world y
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

			// entity's solidArea
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
	            direction = "down";
	        }
			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if (enTopY < nextY && enLeftX > nextX){
				
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX){
				
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			
			// Stop moving when reach the goal
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if(nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}
}