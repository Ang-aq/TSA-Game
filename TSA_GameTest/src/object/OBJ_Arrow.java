package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Arrow extends Entity{

	public OBJ_Arrow(GamePanel gp) {
		super(gp);

		name = "Arrow";
		down1 = setup("/objects/Arrow");
		collision = false;
	}
}
