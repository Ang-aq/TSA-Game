package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, fPressed, escPressed;
    public boolean upArrowPressed, downArrowPressed, leftArrowPressed, rightArrowPressed;

    boolean checkDrawTime = false;
    boolean debugMode = false;

    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
    	
    	int code = e.getKeyCode();
    
    	  
    	// Title Screen
    	if(gp.gameState == gp.titleState) {
    		titleState(code);
    	}
    	// Play State
    	else if (gp.gameState == gp.playState) {
    		playState(code);
    	}
    	// Pause State
    	else if (gp.gameState == gp.pauseState) {
    		pauseState(code);
    	}
    	// Dialogue State
    	else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
    		dialogueState(code);
    	}
    	// Character State
    	else if (gp.gameState == gp.characterState) {
    		characterState(code);
    	}
    	// Cutscene State
    	else if (gp.gameState == gp.cutsceneState) {
    		cutsceneState(code);
    	}
    	// Options State
    	else if (gp.gameState == gp.optionsState) {
    		optionsState(code);
    	}
    	// Trade State
    	else if (gp.gameState == gp.tradeState) {
    		tradeState(code);
    	}
    	// Instructions State
    	else if (gp.gameState == gp.instructionsState) {
    		instructionsState(code);
    	}
    	// Choice State
    	else if (gp.gameState == gp.choiceState) {
    		choiceState(code);
    	}
    	// Game Over State
    	else if (gp.gameState == gp.gameOverState) {
    		gameOverState(code);
    	}
//    	 Cutscene State
    	else if (gp.gameState == gp.cutsceneState) {
    		cutsceneState(code);
    	}
    }
    public void titleState(int code) {
        if (gp.gameState == gp.titleState) {

        	if (code == KeyEvent.VK_W) {
        		if(gp.ui.commandNum != 0) {
                	gp.ui.commandNum--;
            	}
        		else {
        			// play sound effect that signals they can't go any further
        		}
        	}
            if (code == KeyEvent.VK_S) {
            	if(gp.ui.commandNum != 2) {
                	gp.ui.commandNum++;
            	}
        		else {
        			// play sound effect that signals they can't go any further
        		}
           	}
            if(code == KeyEvent.VK_F) {
            	if(gp.ui.commandNum == 0) {
            		gp.gameState = gp.cutsceneState;
            		gp.eHandler.playCutscene("/cutscenes/cutscene1.gif", 1000); // 50000
            	}

            	if(gp.ui.commandNum == 1) {
            		gp.gameState = gp.instructionsState;
            	}

            	if(gp.ui.commandNum == 2){
            		System.exit(0);
            	}
            }
        }
    }
    public void playState(int code) {
    	// Play State
        if(gp.gameState == gp.playState) {

        	if (code == KeyEvent.VK_W) {
        		upPressed = true;
        	}

        	if (code == KeyEvent.VK_S) {downPressed = true;}

        	if (code == KeyEvent.VK_A) {
        		leftPressed = true;
        	}

        	if (code == KeyEvent.VK_D) {
        		rightPressed = true;
        	}
        	if (code == KeyEvent.VK_UP) { upArrowPressed = true;}
            if (code == KeyEvent.VK_DOWN) { downArrowPressed = true;}
            if (code == KeyEvent.VK_LEFT) { leftArrowPressed = true;}
            if (code == KeyEvent.VK_RIGHT) {rightArrowPressed = true;}
            
        	if (code == KeyEvent.VK_P) {
        		gp.gameState = gp.pauseState;
        	}

        	if (code == KeyEvent.VK_E) {
        		gp.gameState = gp.characterState;
        	}
        	if (code == KeyEvent.VK_ESCAPE) {
        		gp.gameState = gp.optionsState;
        	}
        	
        	//debugging
        	if (code == KeyEvent.VK_T) {
        		if(checkDrawTime == false) {
        			checkDrawTime = true;
        		}
        		else if (checkDrawTime == true) {
        			checkDrawTime = false;
        		}
        		// Switch to debug mode
        		if(debugMode == false) {
        			debugMode = true;
        		}
        		else if (debugMode == true) {
        			debugMode = false;
        		}
        	}
        	if(debugMode == true) {
        			if(code == KeyEvent.VK_0) { // Living Room
        				gp.eHandler.teleport(0,14,14, false);
        			}
        			if(code == KeyEvent.VK_1) { // Kitchen
        				gp.eHandler.teleport(1,18,11, false);
        			}
        			if(code == KeyEvent.VK_2) { // City Shops
        				gp.eHandler.teleport(2,4,33, false);
        			}
        			if(code == KeyEvent.VK_3) { // Shop
        				gp.eHandler.teleport(3,9,15, true);
        			}
        			if(code == KeyEvent.VK_4) { // Bus
        				gp.eHandler.teleport(4,18.5,8.5,false);
        			}
        			if(code == KeyEvent.VK_5) { // Courtyard
        				gp.eHandler.teleport(5,7,14, false);
        			}
        			if(code == KeyEvent.VK_6) { // School
        				gp.eHandler.teleport(6,12,23.5,false);
        			}
        			if(code == KeyEvent.VK_7) { // Roof
        				gp.eHandler.teleport(7,3,3, false);
        			}
        	}


        	if (code == KeyEvent.VK_F) {
        		 fPressed = true;
        	}
        }
    }
    public void pauseState(int code) {
    	if (code == KeyEvent.VK_P) {
    		gp.gameState = gp.playState;
    	}
    }
    public void instructionsState(int code) {
        if (code == KeyEvent.VK_F) {
            gp.gameState = gp.titleState; 
        }
    }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_F) {
            gp.keyH.fPressed = true;
        }
    }
    public void choiceState(int code){
    	if (gp.eHandler.waitingForChoice) {
    		if (code == KeyEvent.VK_F) {

    			gp.ui.choiceActive = true;
    			gp.eHandler.waitingForChoice = false; 
            }
    	} 
        	// Handle player choice selection
    	if (code == KeyEvent.VK_W) {
    		gp.ui.selectedChoice = 0;
    	}
    	if (code == KeyEvent.VK_S) {
    		gp.ui.selectedChoice = 1;
            }
    	if (code == KeyEvent.VK_F) {
    		gp.ui.choiceActive = false;
    		gp.gameState = gp.dialogueState;
    		gp.eHandler.handleChoice();
    	}
    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_E) {
            gp.gameState = gp.playState;
        }

        playerInventory(code);
    }
    public void cutsceneState(int code) {
//        if (code == KeyEvent.VK_ESCAPE) {
//            System.out.println("ESC pressed during cutscene.");
//
//            // Stop the cutscene if it's playing
//            if (gp.eHandler.isCutscenePlaying) {
//                gp.eHandler.stopCutscene(); // Stops cutscene
//            }
//        }
    }
    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_F) {
            switch (gp.ui.commandNum) {
                case 1: // Full Screen Option
                    gp.toggleFullScreen();
                    break;
                case 4: // Back Option
                    gp.gameState = gp.playState;
                    break;
            }
        }

        int maxCommandNum = 4; 

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
    }
    public void tradeState(int code) {
    	
    	if(code == KeyEvent.VK_F) {
    		fPressed = true;
    	}
    	
    	if(gp.ui.subState == 0) {
    		if(code == KeyEvent.VK_W) {
    			gp.ui.commandNum--;
    			if(gp.ui.commandNum < 0) {
    				gp.ui.commandNum = 1;
    			}
//    			gp.playSE(code);
    		}
    		if(code == KeyEvent.VK_S) {
    			gp.ui.commandNum++;
    			if(gp.ui.commandNum > 1) {
    				gp.ui.commandNum = 0;
    			}
//    			gp.playSE(code);
    		}
    	}
    	if(gp.ui.subState == 1) {
        	
        	npcInventory(code);
        	
        	if(code == KeyEvent.VK_ESCAPE) {
        		escPressed = true;
        	}
        }
    }
    public void gameOverState(int code) {
    	if (code == KeyEvent.VK_R) {
            gp.gameState = gp.playState;
            // Reset game state as needed
        } 
    }
    public void playerInventory(int code) {
    	
    	// todo fix later
    	if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
//                gp.playSE();
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
//                gp.playSE();
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
//                gp.playSE();
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
//                gp.playSE();
            }
        }
    }
    public void npcInventory(int code) {
    	System.out.println("key"+ code);
    	if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
//                gp.playSE();
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
//                gp.playSE();
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
//                gp.playSE();
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
//                gp.playSE();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_UP) {
            upArrowPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downArrowPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftArrowPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightArrowPressed = false;
        }

     }
    @Override
	public void keyTyped(KeyEvent e) {
        // Not used
    }
}