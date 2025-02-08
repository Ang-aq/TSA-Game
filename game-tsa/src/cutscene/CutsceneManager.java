package cutscene;

import javax.swing.Timer;

import entity.Entity;
import entity.NPC_Parker;
import entity.PlayerDummy;
//import entity.PlayerDummy;
import main.GamePanel;
import object.OBJ_Rock;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CutsceneManager {
	public GamePanel gp;
	Graphics2D g2;
    Entity CutsceneMaster;
	public int sceneNum;
	public int scenePhase;
    public boolean cutsceneDialogueComplete = false;
    public int delay = 0;

    // Scene Numbers
    public final int NA = 0;
    public final int transformation = 1;
    public final int help = 2;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
        
        CutsceneMaster = new Entity(gp);
        setDialogue();
    }
	public void setDialogue() {
		
		CutsceneMaster.dialogues[1][0] = "[WALLY]\nWait is that...?";
		CutsceneMaster.dialogues[1][1] = "[WALLY]\nParker, what are you doing here?";
		CutsceneMaster.dialogues[1][2] = "[PARKER]\n...";
		CutsceneMaster.dialogues[1][3] = "[PARKER]\nThe Blood Red Moon has arisen upon us\nThe storm approaches...";
		CutsceneMaster.dialogues[1][4] = "[WALLY]\n...\nWhat?";
		CutsceneMaster.dialogues[1][5] = "[PARKER]\nMessorum Tempestus!";


	}
    public void draw(Graphics2D g2) {
    	this.g2 = g2;
    	
    	switch(sceneNum) {
    	case transformation: scene_transformation(); break;
    	}
    }
    public void scene_transformation() {
    	
    	if(scenePhase == 0) {
    		
    		gp.ui.npc.transformed = true;
    		
    		for(int i = 0; i < gp.obj[1].length; i++) {
    			
    			if(gp.obj[gp.currentMap][i] == null) {
    				
    				gp.obj[gp.currentMap][i] = new OBJ_Rock(gp);
    				gp.obj[gp.currentMap][i].worldX = gp.tileSize*13;
    				gp.obj[gp.currentMap][i].worldY = gp.tileSize*1;
    				gp.obj[gp.currentMap][i].temp = true;
    				
    				i++;
    				gp.obj[gp.currentMap][i] = new OBJ_Rock(gp);
    				gp.obj[gp.currentMap][i].worldX = gp.tileSize*14;
    				gp.obj[gp.currentMap][i].worldY = gp.tileSize*1;
    				gp.obj[gp.currentMap][i].temp = true;
    				
    				i++;
    				gp.obj[gp.currentMap][i] = new OBJ_Rock(gp);
    				gp.obj[gp.currentMap][i].worldX = gp.tileSize*15;
    				gp.obj[gp.currentMap][i].worldY = gp.tileSize*1;
    				gp.obj[gp.currentMap][i].temp = true;
    				
    				i++;
    				gp.obj[gp.currentMap][i] = new OBJ_Rock(gp);
    				gp.obj[gp.currentMap][i].worldX = gp.tileSize*16;
    				gp.obj[gp.currentMap][i].worldY = gp.tileSize*1;
    				gp.obj[gp.currentMap][i].temp = true;
    				break;
    			}
    		}
    		
    		for(int i = 0; i < gp.npc[1].length; i++) {
    			
    			if(gp.npc[gp.currentMap][i] == null) {
    				
    				gp.npc[gp.currentMap][i] = new NPC_Parker(gp);
    				gp.npc[gp.currentMap][i].worldX = (int)(gp.tileSize*14.5);
    				gp.npc[gp.currentMap][i].worldY = gp.tileSize*4;
    				break;
    			}
    		}
    		
    		for(int i = 0; i < gp.npc[1].length; i++) {
    			
    			if(gp.npc[gp.currentMap][i] == null) {
    				gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
    				gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
    				gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
    				gp.npc[gp.currentMap][i].direction = gp.player.direction;
    				break;
    			}
    		}
    		
    		gp.player.drawing = false;
    		scenePhase++;
    	} 
    	if(scenePhase == 1) {
    		
    		gp.player.worldY -= 2;
    		
    		if(gp.player.worldY < gp.tileSize * 3) {
    			scenePhase++;
    		}
    	}
    	if(scenePhase == 2) {
    		
    		CutsceneMaster.startDialogue(CutsceneMaster, 1, () -> {
    			cutsceneDialogueComplete = true;
    			
    		});
			scenePhase++;
			
    	}
    	if(scenePhase == 3) {
    		
    		if(cutsceneDialogueComplete == true) {
    			
    			gp.eHandler.playCutscene("/res/cutscenes/cutscene6.gif", 70000, () ->{
        			
    				gp.eHandler.teleport(7, 2, 2, false);
        			scenePhase++;
    			});
    		}
    	}
    	if(scenePhase == 4) {
    		
    		for(int i = 0; i < gp.npc[1].length; i++) {
    			if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == PlayerDummy.npcName) {
    				// Restore players position
    				gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
    				gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
    				// Delete the dummy
    				gp.npc[gp.currentMap][i] = null;
    				break;
    			}
    		}	
    		
    		gp.player.drawing = true;
    		
    		sceneNum = NA;
    		scenePhase = 0;
    		gp.gameState = gp.playState;
    	}
    }
}