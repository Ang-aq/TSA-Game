package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StoreShelf extends Entity{

	public OBJ_StoreShelf(GamePanel gp) {
		super(gp);

		name = "Store Shelf";
		down1 = setup("/objects/store_shelf");
		collision = true;
	}
}
