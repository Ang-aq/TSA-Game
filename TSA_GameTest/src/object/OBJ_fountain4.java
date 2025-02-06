package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_fountain4 extends Entity{

	public OBJ_fountain4(GamePanel gp) {
		super(gp);
		
		direction = "down";
		name = "fountain4";
		down1 = setup("/objects/fountain4");
		collision = false;
	}
}
