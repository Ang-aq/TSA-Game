package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

	public static final String npcName = "Dummy";
	
	public PlayerDummy(GamePanel gp) {
		super(gp);
		
		name = npcName;
		getImage();
	}
	
	public void getImage() {

		up1 = setup("/player/MCBack1");
		up2 = setup("/player/MCBack2");
		up3 = setup("/player/MCBack3");
		down1 = setup("/player/MCWalking1");
		down2 = setup("/player/MCWalking2");
		down3 = setup("/player/MCWalking3");
		left1 = setup("/player/MCLeft2");
		left2 = setup("/player/MCLeft1");
		left3 = setup("/player/MCLeft3");
		right1 = setup("/player/MCRight3");
		right2 = setup("/player/MCRight2");
		right3 = setup("/player/MCRight1");
	}
} 
