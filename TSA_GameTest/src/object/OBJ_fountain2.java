package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_fountain2 extends Entity{

	public OBJ_fountain2(GamePanel gp) {
		super(gp);
		
		direction = "down";
		name = "fountain2";
		down1 = setup("/objects/fountain2");
		collision = false;
	}
}
