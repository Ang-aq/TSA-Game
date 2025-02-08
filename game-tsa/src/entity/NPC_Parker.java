package entity;

import main.GamePanel;


public class NPC_Parker extends Entity{

	public NPC_Parker(GamePanel gp) {
		super(gp);

		direction = "up";
		speed = 2;

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Parker2");
		up2 = setup("/npc/NPC_Parker2");
		down1 = setup("/npc/NPC_Parker3");
		down2 = setup("/npc/NPC_Parker3");
		left1 = setup("/npc/NPC_Parker4");
		left2 = setup("/npc/NPC_Parker4");
		right1 = setup("/npc/NPC_Parker1");
		right2 = setup("/npc/NPC_Parker1");
	}

	public void setDialogue() {

		dialogues[1][0] = "Bossfight dialogue here";
	}

	@Override
	public void setAction() {
	    if (onPath) {
	        speed = 3;

	        // Recalculate path every 30 frames
	        if (gp.FPS % 30 == 0) {
	            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
	            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

	            // Stop following if the NPC is close to the player
	            int npcCol = (worldX + solidArea.x) / gp.tileSize;
	            int npcRow = (worldY + solidArea.y) / gp.tileSize;

	            if (npcCol == goalCol && npcRow == goalRow) {
	            	
	                onPath = false; // Stop following
	            } else {
	                searchPath(goalCol, goalRow);
	            }
	        }
	    } else {
	        speed = 0;
	    }
	}
	@Override
	public void speak() {

		onPath = true;
	}
}