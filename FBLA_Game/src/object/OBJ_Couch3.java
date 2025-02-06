package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Couch3 extends Entity{

	public OBJ_Couch3(GamePanel gp) {
		super(gp);

		name = "Couch3";
		down1 = setup("/objects/Couch3");
		collision = false;
	}
}
