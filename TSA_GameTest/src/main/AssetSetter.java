package main;

import entity.NPC_Bully;
import entity.NPC_Clerk;
import entity.NPC_HomelessMan;
import entity.NPC_Miku;
import entity.NPC_Mom;
import entity.NPC_Parker;
import entity.NPC_Principal;
import entity.NPC_Ron;
import entity.NPC_Students;
import entity.NPC_Teacher;
import object.OBJ_Couch;
import object.OBJ_Couch2;
import object.OBJ_Couch3;
import object.OBJ_SchoolWall;
import object.OBJ_StoreShelf;
import object.OBJ_StoreShelf1;
import object.OBJ_StoreShelf2;
import object.OBJ_desk;
import object.OBJ_fountain;
import object.OBJ_fountain2;
import object.OBJ_fountain3;
import object.OBJ_fountain4;
import object.OBJ_locker;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;

	}

	public void setObject() {
	    int mapNum = 0;

	    // Add objects to map 0
	    int i = 0;
	    gp.obj[mapNum][i] = new OBJ_Couch(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 17;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 12;
	    i++;

	    gp.obj[mapNum][i] = new OBJ_Couch2(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 18;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 12;
	    i++;

	    gp.obj[mapNum][i] = new OBJ_Couch3(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 19;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 12;
	    i++;

	    mapNum = 1;
	    i = 0;

	    // Add objects to map 1
	    
	    mapNum = 2;
	    i = 0;
	    
	    mapNum = 3;
	    i = 0;
	    
	    gp.obj[mapNum][i] = new OBJ_desk(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 6;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 15;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_StoreShelf(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 13;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 15;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_StoreShelf1(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 13;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 14;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_StoreShelf2(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 13;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 13;
	    i++;
	    
	    mapNum = 5;
	    i = 0;
	    
	    gp.obj[mapNum][i] = new OBJ_fountain(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 13;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 9;
	    i++;
	    gp.obj[mapNum][i] = new OBJ_fountain2(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 14;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 9;
	    i++;
	    gp.obj[mapNum][i] = new OBJ_fountain3(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 15;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 9;
	    i++;
	    gp.obj[mapNum][i] = new OBJ_fountain4(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 16;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 9;
	    i++;
	    
	    mapNum = 6;
	    i = 0;
	    
	    gp.obj[mapNum][i] = new OBJ_SchoolWall(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 20;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_SchoolWall(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 19;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_locker(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 17;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_locker(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 16;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_SchoolWall(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 15;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_locker(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 10;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_locker(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 9;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_locker(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 6;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	    
	    gp.obj[mapNum][i] = new OBJ_SchoolWall(gp);
	    gp.obj[mapNum][i].worldX = gp.tileSize * 5;
	    gp.obj[mapNum][i].worldY = gp.tileSize * 32;
	    i++;
	}

	public void setNPC() {

		int mapNum = 0;
		int i = 0;

//		gp.npc[mapNum][i] = new NPC_tester(gp);
//		gp.npc[mapNum][i].worldX = gp.tileSize*21;
//		gp.npc[mapNum][i].worldY = gp.tileSize*21;
//		i++;

		mapNum = 1;
		i = 0;
		// add NPCs to map 1 here

		gp.npc[mapNum][i] = new NPC_Mom(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*14;
		gp.npc[mapNum][i].worldY = gp.tileSize*9;
		i++;
		
		mapNum = 2;
		i = 0;
		// add NPCs to map 2 here
		
		gp.npc[mapNum][i] = new NPC_HomelessMan(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*12;
		gp.npc[mapNum][i].worldY = gp.tileSize*32;
		i++;

		mapNum = 3;
		i = 0;
		// add NPCs to map 3 here

		gp.npc[mapNum][i] = new NPC_Clerk(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*6;
		gp.npc[mapNum][i].worldY = gp.tileSize*14;
		i++;
		
		mapNum = 5;
		i = 0;
		// add NPCs to map 5 here
		gp.npc[mapNum][i] = new NPC_Students(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*12;
		gp.npc[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.npc[mapNum][i] = new NPC_Miku(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*17;
		gp.npc[mapNum][i].worldY = (int)(gp.tileSize*11.8);
		i++;
		gp.npc[mapNum][i] = new NPC_Ron(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*18;
		gp.npc[mapNum][i].worldY = gp.tileSize*8;
		i++;
		gp.npc[mapNum][i] = new NPC_Bully(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*11;
		gp.npc[mapNum][i].worldY = gp.tileSize*8;
		i++;
		gp.npc[mapNum][i] = new NPC_Parker(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*18;
		gp.npc[mapNum][i].worldY = (int)(gp.tileSize*8.8);
		i++;
		
		mapNum = 6;
		i = 0;
		
		// add NPCs to map 6 here
		gp.npc[mapNum][i] = new NPC_Students(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*19;
		gp.npc[mapNum][i].worldY = (int)(gp.tileSize*26.5);
		i++;
		
		if(gp.eHandler.helpKid == true) {
			gp.npc[mapNum][i] = new NPC_Parker(gp);
			gp.npc[mapNum][i].worldX = gp.tileSize*12;
			gp.npc[mapNum][i].worldY = gp.tileSize*25;
			i++;
		} else {
			gp.npc[mapNum][i] = new NPC_Parker(gp);
			gp.npc[mapNum][i].worldX = gp.tileSize*9;
			gp.npc[mapNum][i].worldY = gp.tileSize*29;
			i++;
		}
		
		gp.npc[mapNum][i] = new NPC_Teacher(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*8;
		gp.npc[mapNum][i].worldY = (int)(gp.tileSize*26.5);
		i++;
		
		gp.npc[mapNum][i] = new NPC_Bully(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*6;
		gp.npc[mapNum][i].worldY = gp.tileSize*29;
		i++;
		gp.npc[mapNum][i] = new NPC_Ron(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*7;
		gp.npc[mapNum][i].worldY = gp.tileSize*29;
		i++;
		
		if(gp.eHandler.classComplete) {
			gp.npc[mapNum][i] = new NPC_Students(gp);
			gp.npc[mapNum][i].worldX = gp.tileSize*19;
			gp.npc[mapNum][i].worldY = gp.tileSize*34;
			i++;
			gp.npc[mapNum][i] = new NPC_Principal(gp);
			gp.npc[mapNum][i].worldX = gp.tileSize*19;
			gp.npc[mapNum][i].worldY = gp.tileSize*35;
			i++;
		}
		
		
		mapNum = 7;
		i = 0;
		// add NPCs to map 6 here
		gp.npc[mapNum][i] = new NPC_Parker(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*3;
		gp.npc[mapNum][i].worldY = gp.tileSize*4;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Bully(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*3;
		gp.npc[mapNum][i].worldY = gp.tileSize*3;
		i++;
	}
}
