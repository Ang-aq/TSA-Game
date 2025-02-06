package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_locker extends Entity{

	public OBJ_locker(GamePanel gp) {
		super(gp);

		name = "locker";
		down1 = setup("/objects/locker");
		collision = false;
	}
}
