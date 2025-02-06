package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_desk extends Entity {

	public OBJ_desk(GamePanel gp) {
		super(gp);

		name = "Store Desk";
		down1 = setup("/objects/desk");
		collision = true;
	}

}
