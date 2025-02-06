package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CypressStick extends Entity{

	public OBJ_CypressStick(GamePanel gp) {
		
		super(gp);

		name = "Cypress Stick";
		down1 = setup("/objects/cypress_stick");
		collision = false;
		description = "[" + name + "]\nA practice wand. A good\nchoice for new sorcerers.";
		price = 10;
	}
}
