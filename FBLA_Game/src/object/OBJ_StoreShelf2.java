package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StoreShelf2 extends Entity{

	public OBJ_StoreShelf2(GamePanel gp) {
		super(gp);

		name = "Store Shelf";
		down1 = setup("/objects/store_shelf2");
		collision = false;
	}
}
