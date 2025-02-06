package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Rock extends Entity{

	public OBJ_Rock(GamePanel gp) {
		super(gp);

		name = "Store Shelf";
		down1 = setup("/objects/Rock");
		collision = true;
	}
}
