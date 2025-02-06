 package entity;

import main.GamePanel;
import object.OBJ_CypressStick;
import object.OBJ_EnchantersStaff;


public class NPC_Clerk extends Entity{

	public NPC_Clerk(GamePanel gp) {
		super(gp);

		direction = "down";

		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_Clerk");
		up2 = setup("/npc/NPC_Clerk");
		down1 = setup("/npc/NPC_Clerk");
		down2 = setup("/npc/NPC_Clerk");
		left1 = setup("/npc/NPC_Clerk");
		left2 = setup("/npc/NPC_Clerk");
		right1 = setup("/npc/NPC_Clerk");
		right2 = setup("/npc/NPC_Clerk");
	}

	public void setDialogue() {

		dialogues[0][0] = "Welcome to WandWorks! What can I get for you?";
		dialogues[1][0] = "You don't have enough money to buy that!";
		dialogues[2][0] = "Please come again!";
			
	}

	@Override
	public void setAction() {
	}

	@Override
	public void speak() {

		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;	

	}
	public void setItems() {
		
		inventory.add(new OBJ_CypressStick(gp));
		inventory.add(new OBJ_EnchantersStaff(gp));
	}
}
