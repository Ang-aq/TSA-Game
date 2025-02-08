package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Couch2 extends Entity{

	public OBJ_Couch2(GamePanel gp) {
		super(gp);

		name = "Couch2";
		down1 = setup("/objects/Couch2");
		collision = false;
	}
}
