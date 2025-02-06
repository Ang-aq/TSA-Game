package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wand extends Entity {

	public OBJ_Wand (GamePanel gp) {
	super(gp);

	name = "Wand";
	down1 = setup("/objects/wand");
	attackValue = 1;
	description = "[" + name + "]\nA beginner wand";

	}
}
