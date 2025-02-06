package entity;

import main.GamePanel;


public class NPC_Mom extends Entity{

	public NPC_Mom(GamePanel gp) {
		super(gp);

		direction = "down";
//		speed = 1;

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/MCMom");
		up2 = setup("/npc/MCMom");
		down1 = setup("/npc/MCMom");
		down2 = setup("/npc/MCMom");
		left1 = setup("/npc/MCMom");
		left2 = setup("/npc/MCMom");
		right1 = setup("/npc/MCMom");
		right2 = setup("/npc/MCMom");
	}

	public void setDialogue() {

		dialogues[1][0] = "[MOM]\nOh hello " + gp.player.name + "!";
		dialogues[1][1] = "[MOM]\nWhat does that letter say?";
		dialogues[1][2] = "[TUTORIAL]\nPressing E opens your inventory";

		dialogues[2][0] = "[MOM]\nYou got into Marie Venefica Magic School? \nI'm so proud of you!";
		dialogues[2][1] = "[MOM]\nLets go to the city to get you a wand!";
	}

	@Override
	public void setAction() {

//		actionLookCounter++;
		
//		if(actionLookCounter == 120) {
//			Random random = new Random();
//			int i = random.nextInt(100)+1; // picks a number from 1-100
//
//			if (i <= 25) {
//			direction = "up";
//			}
//			if (i > 25 && i <= 50) {
//				direction = "down";
//			}
//			if (i > 50 && i <= 75) {
//				direction = "left";
//			}
//			if (i > 75 && i <= 100) {
//				direction = "right";
//			}
//
//			actionLookCounter = 0;
//		}

//		UNUSED

	}

	@Override
	public void speak() {

		facePlayer();
		startDialogue(this, dialogueSet);

		dialogueSet++;

		if (dialogues[dialogueSet][0] == null) {

			dialogueSet--;
		}
	}
}
