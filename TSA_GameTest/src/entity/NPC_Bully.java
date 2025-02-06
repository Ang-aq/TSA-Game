package entity;

import main.GamePanel;


public class NPC_Bully extends Entity{

	public NPC_Bully(GamePanel gp) {
		super(gp);

		direction = "down";

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Bully");
		up2 = setup("/npc/NPC_Bully");
		down1 = setup("/npc/NPC_Bully");
		down2 = setup("/npc/NPC_Bully");
		left1 = setup("/npc/NPC_Bully");
		left2 = setup("/npc/NPC_Bully");
		right1 = setup("/npc/NPC_Bully");
		right2 = setup("/npc/NPC_Bully");
	}

	public void setDialogue() {

		dialogues[1][0] = "[BULLY]\nWhat do you want?!";
	}

	@Override
	public void setAction() {
		// none
	}

	@Override
	public void speak() {

		facePlayer();  // makes the npc face the player when the player interacts with it. 
		startDialogue(this, dialogueSet);

		dialogueSet++;

		if (dialogues[dialogueSet][0] == null) {

			dialogueSet--;
		}
	}
}
