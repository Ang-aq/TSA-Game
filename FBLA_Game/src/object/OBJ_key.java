package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_key extends Entity{

	public OBJ_key(GamePanel gp) {
		super(gp);

		name = "House Keys";
		down1 = setup("/objects/key");
		collision = false;
		description = "[" + name + "]\nDon't lose these!";
	}
}
