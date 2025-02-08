package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Couch extends Entity{

	public OBJ_Couch(GamePanel gp) {
		super(gp);

		name = "Couch1";
		down1 = setup("/objects/Couch1");
		collision = false;
	}
}
