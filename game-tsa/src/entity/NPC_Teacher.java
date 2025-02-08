package entity;

import main.GamePanel;


public class NPC_Teacher extends Entity{

	public NPC_Teacher(GamePanel gp) {
		super(gp);

		direction = "down";

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Teacher");
		up2 = setup("/npc/NPC_Teacher");
		down1 = setup("/npc/NPC_Teacher");
		down2 = setup("/npc/NPC_Teacher");
		left1 = setup("/npc/NPC_Teacher");
		left2 = setup("/npc/NPC_Teacher");
		right1 = setup("/npc/NPC_Teacher");
		right2 = setup("/npc/NPC_Teacher");
	}

	public void setDialogue() {

		dialogues[1][0] = "[TEACHER]\nHello! Your names Yuno, right?";
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
