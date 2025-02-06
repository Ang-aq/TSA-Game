package entity;

import main.GamePanel;


public class NPC_HomelessMan extends Entity{

	public NPC_HomelessMan(GamePanel gp) {
		super(gp);

		direction = "down";
		getImage();
		setDialogue();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_HomelessMan");
		up2 = setup("/npc/NPC_HomelessMan");
		down1 = setup("/npc/NPC_HomelessMan");
		down2 = setup("/npc/NPC_HomelessMan");
		left1 = setup("/npc/NPC_HomelessMan");
		left2 = setup("/npc/NPC_HomelessMan");
		right1 = setup("/npc/NPC_HomelessMan");
		right2 = setup("/npc/NPC_HomelessMan");
	}

	public void setDialogue() {

		dialogues[1][0] = "";
	}

	@Override
	public void setAction() {


	}

	@Override
	public void speak() {

		facePlayer();
	}
}
