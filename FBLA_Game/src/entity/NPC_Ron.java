package entity;

import main.GamePanel;


public class NPC_Ron extends Entity{

	public NPC_Ron(GamePanel gp) {
		super(gp);

		direction = "down";

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Ron");
		up2 = setup("/npc/NPC_Ron");
		down1 = setup("/npc/NPC_Ron");
		down2 = setup("/npc/NPC_Ron");
		left1 = setup("/npc/NPC_Ron");
		left2 = setup("/npc/NPC_Ron");
		right1 = setup("/npc/NPC_Ron");
		right2 = setup("/npc/NPC_Ron");
	}

	public void setDialogue() {

		dialogues[1][0] = "";
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
