package entity;

import main.GamePanel;


public class NPC_Students extends Entity{

	public NPC_Students(GamePanel gp) {
		super(gp);

		direction = "down";

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Pink");
		up2 = setup("/npc/NPC_Pink");
		down1 = setup("/npc/NPC_Pink");
		down2 = setup("/npc/NPC_Pink");
		left1 = setup("/npc/NPC_Pink");
		left2 = setup("/npc/NPC_Pink");
		right1 = setup("/npc/NPC_Pink");
		right2 = setup("/npc/NPC_Pink");
	}

	public void setDialogue() {

		dialogues[1][0] = "[PRINCIPAL]\nWe must warn everyone immediately..";
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
