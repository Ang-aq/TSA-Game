package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_fountain3 extends Entity{

	public OBJ_fountain3(GamePanel gp) {
		super(gp);
		
		direction = "down";
		name = "fountain3";
		down1 = setup("/objects/fountain3");
		collision = false;
	}
}
