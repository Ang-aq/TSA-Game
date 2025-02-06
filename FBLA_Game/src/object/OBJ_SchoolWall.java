package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SchoolWall extends Entity{

	public OBJ_SchoolWall(GamePanel gp) {
		super(gp);

		name = "wall";
		down1 = setup("/objects/wall");
		collision = false;
	}
}
