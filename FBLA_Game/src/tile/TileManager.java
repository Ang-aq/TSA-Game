package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp) {

    	
    	this.gp = gp;

    	tile = new Tile[500];  // can add more later
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/outdoor.txt",0);
        loadMap("/maps/interior.txt", 1); // Replace with map file path.
        loadMap("/maps/cityshops.txt", 2);
        loadMap("/maps/shop.txt", 3);
        loadMap("/maps/bus.txt", 4);
        loadMap("/maps/courtyard.txt", 5);
        loadMap("/maps/school.txt", 6);
        loadMap("/maps/courtyard2.txt", 7);
        loadMap("/maps/platform.txt", 8);

    }

    public void getTileImage() {

    	setup(0, "0", true);
    	setup(1, "1", false);
    	setup(2, "2", true);
    	setup(3, "3", true);
    	setup(4, "4", true);
    	setup(5, "5", true);
    	setup(6, "6", true);
    	setup(7, "7", true);
    	setup(8, "8", true);
    	setup(9, "9", true);
    	setup(10, "10", true);
    	setup(11, "11", true);
    	setup(12, "12", true);
    	setup(13, "13", true);
    	setup(14, "14", true);
    	setup(15, "15", true);
    	setup(16, "16", true);
    	setup(17, "17", true);
    	setup(18, "18", true);
    	setup(19, "19", true);
    	setup(20, "20", true);
    	setup(21, "21", true);
    	setup(22, "22", true);
    	setup(23, "23", true);
    	setup(24, "24", true);
    	setup(25, "25", true);
    	setup(26, "26", true);
    	setup(27, "27", true);
    	setup(28, "28", true);
    	setup(29, "29", true);
    	setup(30, "30", true);
    	setup(31, "31", true);
    	setup(32, "32", true);
    	setup(33, "33", true);
    	setup(34, "34", true);
    	setup(35, "35", true);
    	setup(36, "36", true);
        setup(37, "37", true);
        setup(38, "38", true);
        setup(39, "39", true);
        setup(40, "40", false);
        setup(41, "41", false);
        setup(42, "42", false);
        setup(43, "43", false);
        setup(44, "44", true);
        setup(45, "45", true);
        setup(46, "46", true);
        setup(47, "47", true);
        setup(48, "48", true);
        setup(49, "49", true);
        setup(50, "50", false);
        setup(51, "51", false);
        setup(52, "52", false);
        setup(53, "53", false);
        setup(54, "54", true);
        setup(55, "55", true);
        setup(56, "56", true);
        setup(57, "57", true);
        setup(58, "58", true);
        setup(59, "59", false);
        setup(60, "60", false);
        setup(61, "61", false);
        setup(62, "62", false);
        setup(63, "63", false);
        setup(64, "64", false);
        setup(65, "65", false);
        setup(66, "66", false);
        setup(67, "67", false);
        setup(68, "68", false);
        setup(69, "69", false);
        setup(70, "70", false);
        setup(71, "71", false);
        setup(72, "72", false);
        setup(73, "73", false);
        setup(74, "74", true);
        setup(75, "75", true);
        setup(76, "76", true);
        setup(77, "77", false);
        setup(78, "78", false);
        setup(79, "79", false);
        setup(80, "80", false);
        setup(81, "81", false);
        setup(82, "82", false);
        setup(83, "83", false);
        setup(84, "84", false);
        setup(85, "85", false);
        setup(86, "86", false);
        setup(87, "87", false);
        setup(88, "88", false);
        setup(89, "89", false);
        setup(90, "90", false);
        setup(91, "91", false);
        setup(92, "92", false);
        setup(93, "93", false);
        setup(94, "94", false);
        setup(95, "95", false);
        setup(96, "96", false);
        setup(97, "97", false);
        setup(98, "98", false);
        setup(99, "99", false);
        setup(100, "100", false);
        setup(101, "101", true);
        setup(102, "102", false);
        setup(103, "103", false);
        setup(104, "104", true);
        setup(105, "105", false);
        setup(106, "106", false);
        setup(107, "107", false);
        setup(108, "108", true);
        setup(109, "109", true);
        setup(110, "110", false);
        setup(111, "111", false);
        setup(112, "112", false);
        setup(113, "113", false);
        setup(114, "114", false);
        setup(115, "115", false);
        setup(116, "116", false);
        setup(117, "117", false);
        setup(118, "118", false);
        setup(119, "119", true);
        setup(120, "120", false);
        setup(121, "121", false);
        setup(122, "122", false);
        setup(123, "123", false);
        setup(124, "124", false);
        setup(125, "125", false);
        setup(126, "126", true);
        setup(127, "127", true);
        setup(128, "128", true);
        setup(129, "129", true);
        setup(130, "130", true);
        setup(131, "131", true);
        setup(132, "132", true);
        setup(133, "133", true);
        setup(134, "134", true);
        setup(135, "135", true);
        setup(136, "136", true);
        setup(137, "137", true);
        setup(138, "138", true);
        setup(139, "139", true);
        setup(140, "140", true);
        setup(141, "141", true);
        setup(142, "142", true);
        setup(143, "143", true);
        setup(144, "144", true);
        setup(145, "145", false);
        setup(146, "146", false);
        setup(147, "147", false);
        setup(148, "148", false);
        setup(149, "149", false);
        setup(150, "150", false);
        setup(151, "151", false);
        setup(152, "152", false);
        setup(153, "153", false);
        setup(154, "154", false);
        setup(155, "155", false);
        setup(156, "156", false);
        setup(157, "157", false);
        setup(158, "158", false);
        setup(159, "159", false);
        setup(160, "160", false);
        setup(161, "161", false);
        setup(162, "162", false);
        setup(163, "163", false);
        setup(164, "164", false);
        setup(165, "165", false);
        setup(166, "166", false);
        setup(167, "167", false);
        setup(168, "168", false);
        setup(169, "169", false);
        setup(170, "170", false);
        setup(171, "171", false);
        setup(172, "172", false);
        setup(173, "173", false);
        setup(174, "174", false);
        setup(175, "175", false);
        setup(176, "176", false);
        setup(177, "177", false);
        setup(178, "178", false);
        setup(179, "179", false);
        setup(180, "180", true);
        setup(181, "181", true);
        setup(182, "182", true);
        setup(183, "183", false);
        setup(184, "184", false);
        setup(185, "185", false);
        setup(186, "186", false);
        setup(187, "187", false);
        setup(188, "188", false);
        setup(189, "189", false);
        setup(190, "190", false);
        setup(191, "191", false);
        setup(192, "192", false);
        setup(193, "193", false);
        setup(194, "194", false);
        setup(195, "195", false);
        setup(196, "196", false);
        setup(197, "197", false);
        setup(198, "198", true);
        setup(199, "199", true);
        setup(200, "200", true);
        setup(201, "201", false);
        setup(202, "202", false);
        setup(203, "203", false);
        setup(204, "204", false);
        setup(205, "205", false);
        setup(206, "206", false);
        setup(207, "207", false);
        setup(208, "208", false);
        setup(209, "209", false);
        setup(210, "210", false);
        setup(211, "211", false);
        setup(212, "212", false);
        setup(213, "213", false);
        setup(214, "214", false);
        setup(215, "215", false);
        setup(216, "216", true);

            //set tiles here: (Tile index, image name, collision)

    }

    public void setup(int index, String imageName, boolean collision) {

    	UtilityTool uTool = new UtilityTool();

    	switch(gp.currentMap) {

    	case 0:
    		if(index > 100) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/outdoor/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    		try {
    	    	tile[index] = new Tile();
    	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/outdoor/" + imageName + ".png"));
    	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	        tile[index].collision = collision;
    	    	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }

    		}
    		break;

    	case 1:

    		if(index > 72) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    		try {
    	    	tile[index] = new Tile();
    	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
    	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	        tile[index].collision = collision;
    	    	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }

    		}
    		break;
    	case 2:

    		if(index > 216) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    		try {
    	    	tile[index] = new Tile();
    	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/city/1 (" + imageName + ").png"));
    	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	        tile[index].collision = collision;
    	    	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }

    		}
    		break;
    	case 3:
    		
    		if(index > 100) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    		try {
    	    	tile[index] = new Tile();
    	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/shop/1 (" + imageName + ").png"));
    	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	        tile[index].collision = collision;
    	    	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }

    		}
    		break;
    	case 4:
    		
    		if(index > 80 || index == 0) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    		try {
    	    	tile[index] = new Tile();
    	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/bus/1 (" + imageName + ").png"));
    	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	        tile[index].collision = collision;
    	    	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }

    		}
    		break;
    	case 5:
    		
    		if(index > 69 || (index > 21 && index < 59)) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    		try {
    	    	tile[index] = new Tile();
    	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/courtyard/1 (" + imageName + ").png"));
    	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	        tile[index].collision = collision;
    	    	}
    	    catch(IOException e) {
    	    	e.printStackTrace();
    	    }

    		}
    		break;
    	case 6:

    		if(index > 216) {
    			try {
    				tile[index] = new Tile();
    	    		tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
    	    		tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    	    		tile[index].collision = collision;
    	    	}
    	    	catch(IOException e) {
    	    		e.printStackTrace();
    	    	}
    		}
    		else {

    			try {
    				tile[index] = new Tile();
    				tile[index].image = ImageIO.read(getClass().getResourceAsStream("/school/1 (" + imageName + ").png"));
    				tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    				tile[index].collision = collision;
    			}
    			catch(IOException e) {
    				e.printStackTrace();
    			}

    		}
    		break;
    	case 7:
    		if(index > 69 || (index > 21 && index < 59)) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    			try {
    				tile[index] = new Tile();
    				tile[index].image = ImageIO.read(getClass().getResourceAsStream("/courtyard/1 (" + imageName + ").png"));
    				tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    				tile[index].collision = collision;
    	    	}
    			catch(IOException e) {
    				e.printStackTrace();
    			}	

    		}
    	break;
    	case 8:
    		if(index > 1 || index == 0) {
    			try {
        	    	tile[index] = new Tile();
        	        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
        	        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        	        tile[index].collision = collision;
        	    	}
        	    	catch(IOException e) {
        	    		e.printStackTrace();
        	    	}
    		}
    		else {

    			try {
    				tile[index] = new Tile();
    				tile[index].image = ImageIO.read(getClass().getResourceAsStream("/platform/1.png"));
    				tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    				tile[index].collision = collision;
    	    	}
    			catch(IOException e) {
    				e.printStackTrace();
    			}	

    		}
    	break;
    		}
    	}
    public void loadMap(String filePath, int map) {


        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            	String line = br.readLine();

                while (col < gp.maxWorldCol) {

                	String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
    	
    	int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

        	int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
        if(drawPath == true) {
        	g2.setColor(new Color(255,0,0,70));
        	
        	for(int i = 0; i < gp.pFinder.pathList.size(); i++) {
        		
        		int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        	}
        }
    }
}