package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_fountain extends Entity{

	public OBJ_fountain(GamePanel gp) {
		super(gp);
		
		direction = "down";
		name = "fountain1";
		down1 = setup("/objects/fountain1");
		collision = false;
	}
}
