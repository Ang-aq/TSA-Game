package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_coin extends Entity {

	public OBJ_coin (GamePanel gp) {
	super(gp);

	name = "Coin";
	down1 = setup("/objects/coin");
	description = "[" + name + "]\nA form of currency";

	}
}
