package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_EnchantersStaff extends Entity{

	public OBJ_EnchantersStaff(GamePanel gp) {
		
		super(gp);

		name = "Enchanters Staff";
		down1 = setup("/objects/enchanters_staff");
		collision = false;
		description = "[" + name + "]\nA powerful beginner wand.\nThe best choice for new\nsorcerers.";
		price = 30;
	}
}
