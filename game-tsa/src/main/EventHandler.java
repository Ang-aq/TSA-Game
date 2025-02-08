package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import entity.Entity;
import tile.TileManager;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][][];
	TileManager tm1;
	Entity eventMaster;
	public boolean isCutscenePlaying = false;
	public ImageIcon cutsceneGif;
	public Timer cutsceneTimer;
	public boolean teleportActive = true;
	public boolean momDialogueComplete = false;
	public boolean momDialogueComplete1 = false;
	public boolean dialogueTriggered = false;
	public boolean homelessmanCanComplete = true;
	public boolean hmDialogueComplete = false;
	public boolean busComplete = true;
	public boolean cutscene5Complete = true;
	public boolean waitingForChoice = false;
	public boolean busDialogueComplete = false;
	public boolean helpKid = false;
	public boolean classComplete = false;
	public boolean ptDialogueComplete = false; 
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;


	public EventHandler(GamePanel gp, TileManager tm1) {
		this.gp = gp;
		this.tm1 = tm1;
		eventMaster = new Entity(gp);

		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];


		int map = 0;
		int col = 0;
		int row = 0;

		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 48;
			eventRect[map][col][row].height = 48;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;

				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		setDialogue();
	}
	public void setDialogue() {

		eventMaster.dialogues[0][0] = "You've obtained an Acceptance Letter!";
		eventMaster.dialogues[1][0] = "[TUTORIAL]\nPress F to interact with objects\nand characters!";
		eventMaster.dialogues[1][1] = "[TUTORIAL]\nKeybinds can be changed in OPTIONS.\nPressing ESC opens the OPTIONS menu.";
		eventMaster.dialogues[1][2] = "[TUTORIAL]\nTry interacting with objects or characters\nby facing them and pressing F.";
		eventMaster.dialogues[2][0] = "[HOMELESS MAN]\nHey, excuse me...";
		eventMaster.dialogues[14][0] = "[HOMELESS MAN]\nOh... okay.";
		eventMaster.dialogues[15][0] = "[HOMELESS MAN]\nThank you very much young man!";
		eventMaster.dialogues[15][1] = "You give the man a 20 dollar bill.";
		eventMaster.dialogues[16][0] = "[HOMELESS MAN]\nHey, can you help me out? I need some\nmoney to buy some food...";
		eventMaster.dialogues[17][0] = "[BUS DRIVER]\nPlease take a seat";
		eventMaster.dialogues[17][1] = "[SHY KID]\n...";
		eventMaster.dialogues[17][2] = "[WALLY]\nHi... I'm Wally";
		eventMaster.dialogues[17][3] = "[BULLY]\nHEY YOU!";
		eventMaster.dialogues[17][4] = "[BULLY]\nDon't sit with that guy,\nhe can't even use simple magic.";
		eventMaster.dialogues[18][0] = "The shy kid was surprised you\nsat with him.";
		eventMaster.dialogues[18][1] = "[WALLY]\nWhat kind of magic do you use?";
		eventMaster.dialogues[18][2] = "[WALLY]\n...";
		eventMaster.dialogues[18][3] = "[WALLY]\nYou don't use any magic?";
		eventMaster.dialogues[19][0] = "You take a seat with the bully";
		eventMaster.dialogues[19][1] = "[BULLY]\nWhat kind of magic do you use?";
		eventMaster.dialogues[19][2] = "*You tell him how you can't use \nany magic*";
		eventMaster.dialogues[19][3] = "[BULLY]\nWhat? You can't?";
		eventMaster.dialogues[19][4] = "[BULLY]\nAnd I thought you were tough!\nGo sit with that dummy over there.";
		eventMaster.dialogues[19][5] = "[BULLY]\nYou two would get along...";
		eventMaster.dialogues[21][0] = "[BULLIED KID]\nThanks for helping me!";
		eventMaster.dialogues[21][1] = "[PARKER]\nI'm Parker. I'm also new here!";
		eventMaster.dialogues[21][2] = "You became friends with Parker!";
		eventMaster.dialogues[22][0] = "You hear the principle and a teacher talking\n in a serious tone accross the hall.";
		eventMaster.dialogues[23][0] = "You should probably go hear what they're\ntalking about";
		eventMaster.dialogues[24][0] = "[TEACHER]\nThats very concerning.";
		eventMaster.dialogues[24][1] = "[PRINCIPAL]\nYes... well, we'll have to up the\ndefense of the school before he comes.";
		eventMaster.dialogues[24][2] = "[TEACHER]\nIf he gets into the school, theres no\ntelling what he can do.";
		eventMaster.dialogues[24][3] = "A loud noise is heard from the courtyard";
		eventMaster.dialogues[24][4] = "[PRINCIPAL]\nWe might be too late...!";

		
		// Object Descriptions
		eventMaster.dialogues[3][0] = "A squeaky clean kitchen sink. Thanks mom!";
		eventMaster.dialogues[4][0] = "The grandfather clock reads 3 PM.";
		eventMaster.dialogues[5][0] = "An old piano. The word OMORI is etched across\nthe center.";
		eventMaster.dialogues[6][0] = "Don't sit down now, you have work to do!";
		eventMaster.dialogues[7][0] = "A stove for cooking meals.";
		eventMaster.dialogues[8][0] = "A flower shop. The sign inside reads\nWe're Open!";
		eventMaster.dialogues[9][0] = "A regular book store. Sadly they dont sell \nspellbooks.";
		eventMaster.dialogues[10][0] = "An apartment building. Woah, so fancy...\n";
		eventMaster.dialogues[11][0] = "Hmm that thing looks dangerous... its best not \nto touch it.";
		eventMaster.dialogues[12][0] = "A shelf full of time related magic items.";
		eventMaster.dialogues[13][0] = "[SHOPKEEPER]\nHey, be careful! You break it you buy it kid.";
		eventMaster.dialogues[25][0] = "A school locker. This one isn't yours!";
		eventMaster.dialogues[26][0] = "A school desk";
		eventMaster.dialogues[27][0] = "A shelf full of beginner spell books";
		eventMaster.dialogues[28][0] = "A shelf full of advanced spell books";
		eventMaster.dialogues[29][0] = "What a nice view!";
		eventMaster.dialogues[30][0] = "A dusty chalkboard";

	}
	public void checkEvent() {

		// Check if the player is more than 1 tile away from last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}

		if(canTouchEvent) {
			
			// Map 0 Living Room:
			
			// Living room to kitchen
			if(teleportActive && hit(0,13,14,"any")||hit(0,13,15,"any")||hit(0,13,13,"any")) {
				teleport(1,18,11, false);
				updateObjective(0,13,14,1,2);
				}
			
			// Go to kitchen objective
			else if(hit(0,20,11,"any")){
				updateObjective(0,20,11,0,1);
			}
			
			// Teleport to city
			else if(teleportActive && hit(0,21,11,"up")  && gp.ui.currentObjective == 3) {
				playCutscene("/res/cutscenes/cutscene2.gif", 22500); // 22500
				teleport(2,4,33, false);
				updateObjective(2,4,33,3,4);
			}
			// Map 1 Kitchen:
			
			// Kitchen to living room
			else if(teleportActive && hit(1,19,10,"any")) {teleport(0,14,14, false);}
			else if(teleportActive && hit(1,19,11,"any")) {teleport(0,14,14, false);}
			else if(teleportActive && hit(1,19,12,"any")) {teleport(0,14,14, false);}
			
			// Interact Tutorial
			else if(hit(1,16,12,"any")||hit(1,16,11,"any")||hit(1,16,10,"any")||hit(1,16,9,"any")) {
				prompt(1,16,12,gp.dialogueState,1);
				prompt(1,16,11,gp.dialogueState,1);
				prompt(1,16,10,gp.dialogueState,1);
				prompt(1,16,9,gp.dialogueState,1);}
			
			// Leave the house
			else if (hit(1, 15, 9, "any") || hit(1, 14, 10, "any") || hit(1, 15, 10, "any")) {
				if (gp.ui.npc.dialogueSet == 2) {
					updateObjective(1, 15, 9, 2, 3);
					updateObjective(1, 15, 10, 2, 3);
					updateObjective(1, 14, 10, 2, 3);
					gp.player.coin = 30;
				}
			}	
			// Map 2 City Shops:
			
			// Teleport to shop
			else if(hit(2,15,33,"any")) {
				
				if(gp.keyH.fPressed){
					
					if(teleportActive) {
						
						teleport(3,9,15, true);
						updateObjective(3,9,15,6,7);
					}
				}	
			}
			
			// Homelessman prompt
			else if (hit(2,14,33,"any")||hit(2,14,34,"any")) {
				if(homelessmanCanComplete) {
					questsNotComplete(gp.dialogueState, 2);
					updateObjective(2,13,33,4,5);
				}
			}
			
			// Homeless Man Question
			else if (hit(2, 12, 33, "up")) {
			    if (homelessmanCanComplete) {
			        if (gp.keyH.fPressed) {
			        	prompt(2,47,33,gp.dialogueState,16);
			        	hmDialogueComplete = true;
			        }
			    }
			}		
			else if (hit(2,12,33,"any")) {
				if (hmDialogueComplete) {
					startChoiceDialogue("No, sorry...", "Sure!", false);
		            updateObjective(2, 12, 36, 5, 6);
		            hmDialogueComplete = false;
				}
			}
			//Map 3 Magic Shop:	
	
			// Shop Clerk
			else if(hit(3,6,16,"up")||hit(3,8,14,"left")) {	
				if (gp.keyH.fPressed) {
					updateObjective(3,6,23,7,8);
					speak(gp.npc[3][0]);
				}
			}
			
			// Teleport to Bus cutscene & seating option
			else if(hit(3,10,17,"down")||hit(3,9,18,"down")) {
				if (gp.ui.currentObjective == 8) {
	    			teleport(4,18.5,8.5,false);
	    			homelessmanCanComplete = false;
					updateObjective(3,6,16,8,9);
	    			playCutscene("/res/cutscenes/cutscene3.gif", 24000); // 24000
		    		eventMaster.startDialogue(eventMaster, 17);
		    		busDialogueComplete = true;	
				}
			}
			// Map 4 Bus:
			else if (hit(4,18,9,"any")||hit(4,19,9,"any")) {
				if (busDialogueComplete && eventMaster.dialogueIndex == 0) {
					startChoiceDialogue("Sit with the shy kid","Sit with the bully", false);
	    		}
			}
			// Map 5 Courtyard:
			else if (gp.keyH.fPressed && (hit(5,17,8, "left")||hit(5,17,8,"right"))) {
	    		prompt(5,39,8,gp.dialogueState,21);
			}
			else if (hit(5,13,13,"any")||hit(5,14,13,"any")||hit(5,15,13,"any")||hit(5,16,13,"any")) {
				transformation();
			}
			
			// Map 6 School:
			else if(hit(6,12,16,"any")) {
				updateObjective(6,12,16,9,10);
			}
			// Bully in the hallway scene
			else if (hit(6,11,20,"any")||hit(6,12,20, "any")||hit(6,13,20, "any")||hit(6,14,20, "any")) {
				if(classComplete == false) {
					eventRect[6][11][20].eventDone = true;
					eventRect[6][12][20].eventDone = true;
					eventRect[6][13][20].eventDone = true;
					eventRect[6][14][20].eventDone = true;
					teleport(8,(int)(3.5),1,false);
					playCutscene("/res/cutscenes/cutscene5.gif", 28500, () -> { // 28500
						homelessmanCanComplete = false;
						busComplete = false;
						startChoiceDialogue("Help", "Walk away", false);
					});
				}
			}
			// Start class screen. fades in and out currently. add more later maybe
			else if(hit(6,7,33,"any")||hit(6,8,33,"any")) {
				fadeToBlack(6,8,33);
				fadeToBlack(6,7,33);
				fadeToNormal(6,8,33);
				fadeToNormal(6,7,33);
				updateObjective(6,49,49,10,11);
				classComplete = true; // Class is now over in game.
	            gp.aSetter.setNPC();
			}
			// Teacher and principal accross the hallway. player has to go there
			else if(hit(6,10,34,"any")||hit(6,10,35,"any")) {
				if(classComplete == true) {
					prompt(6,10,34,gp.dialogueState,22);
					prompt(6,10,35,gp.dialogueState,22);
					updateObjective(6,48,48,11,12);
				}
			}
			// Makes sure the player can't skip ahead 
			else if(hit(6,11,33,"any")||hit(6,12,33,"any")||hit(6,13,33,"any")||hit(6,14,33,"any")) {
				if(classComplete == true) {
					if (ptDialogueComplete == false) {
						questsNotComplete(gp.dialogueState, 23);
					}
				}
			}
			// Teacher and principal dialogue & go to courtyard objective
			else if(hit(6,15,34,"any")||hit(6,15,35,"any")) {
				
				if(classComplete == true) {
					prompt(6,15,34,gp.dialogueState,24);
					prompt(6,15,35,gp.dialogueState,24);
					updateObjective(6,48,49,12,13);
					ptDialogueComplete = true;
				}
			}
			// Teleport to courtyard
			else if(hit(6,11,22,"any")||hit(6,12,22,"any")||hit(6,13,22,"any")||hit(6,14,22,"any")){
				if(classComplete == true) {
					teleport(5,16,14, false);
				}
			}
			else if (hit(6,18,33,"any")) {
				
			}
			// Map 7 Courtyard Puzzle:
			else if (hit(7,3,3,"any")) {
				System.out.println("hit");
			}
			
			// Object Interactions
			else if (hit(1,17,9,"up")) {object(gp.dialogueState,3);}
			else if (hit(0,18,12,"up")) {object(gp.dialogueState,4);}
			else if (hit(0,15,12,"up")||hit(0,16,12,"up")||hit(0,17,12,"up")) {object(gp.dialogueState,5);}
			else if (hit(0,19,14,"up")||hit(0,17,14,"up")||hit(0,17,14,"left")||hit(1,13,12,"up")||hit(1,12,12,"up")) {object(gp.dialogueState,6);}
			else if (hit(1,17,9,"right")) {object(gp.dialogueState,7);}
			else if (hit(2,6,33,"up")) {object(gp.dialogueState,8);}
			else if (hit(2,9,33,"up")) {object(gp.dialogueState,9);}
			else if (hit(2,20,33,"up")) {object(gp.dialogueState,10);}
			else if (hit(3,12,15,"right")) {object(gp.dialogueState,11);}
			else if (hit(3,12,14,"right")) {object(gp.dialogueState,12);}
			else if (hit(3,7,13,"up")||hit(3,8,13,"up")||hit(3,9,13,"up")||hit(3,10,13,"up")
					||hit(3,11,13,"up")||hit(3,12,13,"up")||hit(3,13,13,"up")) {object(gp.dialogueState,13);}
			else if (hit(6,6,34,"up")||hit(6,9,34,"up")||hit(6,10,34,"up")||hit(6,16,34,"up")||hit(6,17,34,"up")) {object(gp.dialogueState,25);}
			else if (hit(6,17,27,"down")||hit(6,18,28,"left")||hit(6,18,30,"left")||hit(6,17,31,"up")||hit(6,16,30,"right")
					||hit(6,16,29,"up")||hit(6,16,29,"left")||hit(6,15,30,"up")) {object(gp.dialogueState,26);}
			else if (hit(6,5,28,"up")) {object(gp.dialogueState,27);}
			else if (hit(6,17,27,"left")) {object(gp.dialogueState,28);}
			else if (hit(6,10,27,"up")) {object(gp.dialogueState,29);}
			else if (hit(6,17,27,"up")||hit(6,18,27,"up")) {object(gp.dialogueState,30);}

		}
	}
	// Checks if the player is hitting a certain tile
	public boolean hit(int map, int col, int row, String reqDirection) {
	    boolean hit = false;

	    if (map == gp.currentMap) {
	        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
	        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
	        eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
	        eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

	        if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
	            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
	                hit = true;
	            }
	        }

	        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
	        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	        eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
	        eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
	    }

	    return hit; // Returns whether or not the player is hitting the tile
	}
	// Teleports the player to a new map or to a new location
	public void teleport(int map, double col, double row, boolean F) {
	    if (F) {
	        if (gp.keyH.fPressed) {
	            if (!teleportActive) {
	                return;
	            }
	            teleportActive = false;

	            gp.startRoomTransition(() -> {
	                gp.currentMap = map;
	                gp.player.worldX = (int) (gp.tileSize * col);
	                gp.player.worldY = (int) (gp.tileSize * row);

	                // Position Player2 for the specific map
	                if (map == 7) {
	                    gp.player2.worldX = (int) (gp.tileSize * (col + 2)); // Adjust as needed
	                    gp.player2.worldY = (int) (gp.tileSize * row);
	                }

	                previousEventX = gp.player.worldX;
	                previousEventY = gp.player.worldY;

	                tm1.getTileImage();
	                gp.entityList.clear();

	                teleportActive = true;
	            });
	        }
	    } else {
	        if (!teleportActive) {
	            return;
	        }
	        teleportActive = false;

	        gp.startRoomTransition(() -> {
	            gp.currentMap = map;
	            gp.player.worldX = (int) (gp.tileSize * col);
	            gp.player.worldY = (int) (gp.tileSize * row);

	            // Position Player2 for the specific map
	            if (map == 7) {
	                gp.player2.worldX = (int) (gp.tileSize * (col + 2)); // Adjust as needed
	                gp.player2.worldY = (int) (gp.tileSize * row);
	            }

	            previousEventX = gp.player.worldX;
	            previousEventY = gp.player.worldY;

	            tm1.getTileImage();
	            gp.entityList.clear();

	            teleportActive = true;
	        });
	    }
	}
	public void object(int gameState, int des) {
		
		if (gp.keyH.fPressed) {
			gp.gameState = gameState;
	        eventMaster.startDialogue(eventMaster, des);		
		}
	}
	public void prompt(int map, int col, int row, int GameState, int quest) {

		gp.gameState = GameState;
        eventMaster.startDialogue(eventMaster, quest);
        
		eventRect[map][col][row].eventDone = true;
	}
	public void playCutscene(String gifPath, int duration) {
	    isCutscenePlaying = true;
	    cutsceneGif = new ImageIcon(getClass().getResource(gifPath));
	    
	    // Timer for the cutscene
	    cutsceneTimer = new Timer(duration, e -> {
	        isCutscenePlaying = false;

	        if (!dialogueTriggered) { 
	            gp.gameState = gp.dialogueState;
	            eventMaster.startDialogue(eventMaster, 0);
	            dialogueTriggered = true;
	        }

	        ((Timer) e.getSource()).stop();
	    });

	    cutsceneTimer.setRepeats(false);
	    cutsceneTimer.start();
	}
	public void playCutscene(String gifPath, int duration, Runnable onComplete) {
        isCutscenePlaying = true;
        cutsceneGif = new ImageIcon(getClass().getResource(gifPath));

        // Timer for the cutscene
        cutsceneTimer = new Timer(duration, e -> {
            isCutscenePlaying = false;
            onComplete.run(); // Execute the callback after the cutscene
            ((Timer) e.getSource()).stop();
        });

        cutsceneTimer.setRepeats(false);
        cutsceneTimer.start();
    }
	public void stopCutscene() {
	    if (isCutscenePlaying) {
	        isCutscenePlaying = false;

	        if (!dialogueTriggered) { // Trigger dialogue only if it hasn't already been triggered
	            gp.gameState = gp.dialogueState;
	            eventMaster.startDialogue(eventMaster, 0);
	            dialogueTriggered = true;
	        }

	        if (cutsceneTimer != null) {
	            cutsceneTimer.stop();
	        }
	        gp.repaint();
	    }
	}
	public void transformation() {
		
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.transformation;
		
	}
	public void updateObjective(int map, int col, int row, int oldObjective, int newObjective) {
	    if (gp.ui.currentObjective == oldObjective) {
	        gp.ui.hideObjective(oldObjective);
	        gp.ui.showObjective(newObjective);
	        eventRect[map][col][row].eventDone = true;
	    }
	}
	public void questsNotComplete(int GameState, int quest) {
	    
	  gp.gameState = GameState;
	  eventMaster.startDialogue(eventMaster, quest);

	       
	  new Timer(100, new ActionListener() {
		  int steps = 0;
	      int maxSteps = 2; 

	      @Override
	      public void actionPerformed(ActionEvent e) {
	    	  if (steps < maxSteps) {
	                  
	    		  switch (gp.player.direction) {
	    		  case "up": gp.player.worldY += gp.tileSize / 10; break;
	    		  case "down": gp.player.worldY -= gp.tileSize / 10; break;
	    		  case "left": gp.player.worldX += gp.tileSize / 10; break;
	    		  case "right": gp.player.worldX -= gp.tileSize / 10; break; }

	    		  if (steps < gp.tileSize) {steps++;}
	    	  
	    	  } else {
	    		  ((Timer) e.getSource()).stop();
	    	  }
	    	  gp.repaint();
	      }
	  }).start();
	}
	public void startChoiceDialogue(String choice1, String choice2, boolean F) {
	    if (F) {
	        if (gp.keyH.fPressed) {
	            gp.gameState = gp.choiceState;
	            gp.ui.choices[0] = choice1;
	            gp.ui.choices[1] = choice2;
	            gp.ui.selectedChoice = 0;
	            gp.eHandler.waitingForChoice = true;
	        }
	    } else {
	        gp.gameState = gp.choiceState;
	        gp.ui.choices[0] = choice1;
	        gp.ui.choices[1] = choice2;
	        gp.ui.selectedChoice = 0;
	        gp.eHandler.waitingForChoice = true;
	    }
	}
	public void handleChoice() {
		if (homelessmanCanComplete) {
	        if (gp.ui.selectedChoice == 0) {
	            homelessmanCanComplete = false;
	            eventMaster.startDialogue(eventMaster, 14, () -> {
	                gp.ui.choiceActive = false;
	                gp.ui.currentDialogue = "";
	                gp.ui.charIndex = 0;
	                gp.gameState = gp.playState;
	            });
	        } else if (gp.ui.selectedChoice == 1) {
	            homelessmanCanComplete = false;
	            gp.player.coin = 10;
	            eventMaster.startDialogue(eventMaster, 15, () -> {
	                // Callback after dialogue completes
	                gp.ui.choiceActive = false;
	                gp.ui.currentDialogue = "";
	                gp.gameState = gp.playState;
	            });
	        }
	    } else if (busComplete) {
	        if (gp.ui.selectedChoice == 0) {
	            eventMaster.startDialogue(eventMaster, 18, () -> {
	                // This code will run after the dialogue set is complete
	                busComplete = false;
	                teleport(6, 12, 16, false);
	                playCutscene("/res/cutscenes/cutscene4.gif", 24000);
	                gp.ui.choiceActive = false;
	                gp.ui.currentDialogue = "";
	                gp.ui.charIndex = 0;
	                gp.gameState = gp.playState;
	            });
	        } else if (gp.ui.selectedChoice == 1) {
	            eventMaster.startDialogue(eventMaster, 19, () -> {
	                // This code will run after the dialogue set is complete
	                busComplete = false;
	                teleport(6, 12, 16, false);
	                playCutscene("/res/cutscenes/cutscene4.gif", 30000);
	                gp.ui.choiceActive = false;
	                gp.ui.currentDialogue = "";
	                gp.ui.charIndex = 0;
	                gp.gameState = gp.playState;
	            });
	        }
	    } 
	    else if (cutscene5Complete) {
	        if (gp.ui.selectedChoice == 0) {
	        	gp.gameState = gp.dialogueState;
	        	teleport(6,12,23.5,false);
	        	helpKid = true;
	            gp.aSetter.setNPC();
	            eventMaster.startDialogue(eventMaster, 21, () -> {
	            });
	        } else if (gp.ui.selectedChoice == 1) {
	            teleport(6,12,24,false);
	            gp.aSetter.setNPC();
	            gp.gameState = gp.playState;
	        }
	    } 
	    else {
	        gp.gameState = gp.playState;
	    }
	}
	public void fadeToBlack(int map, int col, int row) {
	    gp.isFadingToBlack = true;
	    gp.isFadingToNormal = false;

	    // Use a timer to gradually increase the fadeAlpha
	    new Timer(50, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            gp.fadeAlphaEvents += 0.05f; 
	            if (gp.fadeAlphaEvents >= 1f) {
	                gp.fadeAlphaEvents = 1f; 
	                ((Timer) e.getSource()).stop(); 
	                gp.isFadingToBlack = false; 
	            }
	            gp.repaint();
	        }
	    }).start();
        eventRect[map][col][row].eventDone = true;
	}
	public void fadeToNormal(int map, int col, int row) {
	    gp.isFadingToNormal = true;
	    gp.isFadingToBlack = false;

	    // Use a timer to gradually decrease the fadeAlpha
	    new Timer(50, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            gp.fadeAlphaEvents -= 0.05f;
	            if (gp.fadeAlphaEvents <= 0f) {
	                gp.fadeAlphaEvents = 0f;
	                ((Timer) e.getSource()).stop(); 
	                gp.isFadingToNormal = false; 
	            }
	            gp.repaint();
	        }
	    }).start();
        eventRect[map][col][row].eventDone = true;
	}
	public void speak(Entity entity) {
		
		if(gp.keyH.fPressed == true) {
			 gp.gameState = gp.dialogueState;
			 entity.speak();
		}
	}
}