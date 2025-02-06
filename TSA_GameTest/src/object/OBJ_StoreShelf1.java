package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StoreShelf1 extends Entity{

	public OBJ_StoreShelf1(GamePanel gp) {
		super(gp);

		name = "Store Shelf";
		down1 = setup("/objects/store_shelf1");
		collision = true;
	}
}
