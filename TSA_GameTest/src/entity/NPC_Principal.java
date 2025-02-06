package entity;

import main.GamePanel;


public class NPC_Principal extends Entity{

	public NPC_Principal(GamePanel gp) {
		super(gp);

		direction = "up";

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Principal");
		up2 = setup("/npc/NPC_Principal");
		down1 = setup("/npc/NPC_Principal");
		down2 = setup("/npc/NPC_Principal");
		left1 = setup("/npc/NPC_Principal");
		left2 = setup("/npc/NPC_Principal");
		right1 = setup("/npc/NPC_Principal");
		right2 = setup("/npc/NPC_Principal");
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
