package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_letter extends Entity{

	public OBJ_letter(GamePanel gp) {
		super(gp);

		name = "Acceptance Letter";
		down1 = setup("/objects/letter");
		collision = false;
		description = "[" + name + "]\n\"Congratulations, you've \nbeen accepted to \nMaga Venefica!\"";
	}

}
