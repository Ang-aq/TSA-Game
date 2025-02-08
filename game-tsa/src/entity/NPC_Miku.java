package entity;

import main.GamePanel;


public class NPC_Miku extends Entity{

	public NPC_Miku(GamePanel gp) {
		super(gp);

		direction = "down";

		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Miku");
		up2 = setup("/npc/NPC_Miku");
		down1 = setup("/npc/NPC_Miku");
		down2 = setup("/npc/NPC_Miku");
		left1 = setup("/npc/NPC_Miku");
		left2 = setup("/npc/NPC_Miku");
		right1 = setup("/npc/NPC_Miku");
		right2 = setup("/npc/NPC_Miku");
	}

	public void setDialogue() {

		dialogues[1][0] = "She doesn't want to talk to you...";
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
