// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies
import java.util.Scanner;
import java.util.ArrayList;
import ansi_terminal.*;
import java.io.PrintWriter;
public class Room {
    // the grid holds the room geometry
   private String grid[];
    // the size of the room
   private int rows;
   private int cols;

   public Room() {
	 // this initializes the grid for room one
	this.grid = world.getOne();
			
       // this initializes the room to one specific space
        if (grid == world.getOne()) {
		this.rows = 31;
		this.cols = 57;
	}else if (grid == world.getTwo()) {
		this.rows = 84;
		this.cols = 24;
	}else if (grid == world.getThree()) {
		this.rows = 91;
		this.cols = 28;
	} 

   }
    
    public save(PrintWriter pw)
    {
    // we need to save boxes and enemies.
    pw.println("saving Room below.");
    for (Box box : boxes)
    {box.save();}

    for (Enemy enemy : enemies)
    {enemy.save();}

    }


    // returns the player's strting location in this room
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '@') {
                    return new Position(row, col);
                }
            }
        }

        return null;
    }

    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '^') {
                    boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }

        return boxes;
    }
    // returns a set of teleporters for each map
    public ArrayList<Teleport> getTeleport() {
	ArrayList<Teleport> teleports = new ArrayList<Teleport>();
	for (int row = 0; row < rows; row++) {
	    for (int col = 0; col < cols; col++) {
		if (grid[row].charAt(col) == '*') {
		   teleports.add(new Teleport(row, col));
		   
                }
            }
        }
        return teleports;
    }
    
    // returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '%') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }

        return enemies;
    }
    // returns a boss for the final map
    public ArrayList<Enemy> getBoss() {
       ArrayList<Enemy> boss = new ArrayList<Enemy>();
       for (int row = 0; row <rows; row++){
           for (int col = 0; col < cols; col++){
	       if (grid[row].charAt(col) == '&'){
		   boss.add(new Enemy("Chicken Chimera", row, col, 40, 10, 15));
               }
           }
       }
	return boss; 
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                } else {
                    // whatever else, just draw a blank (we DONT draw starting items from map)
                    System.out.print(' ');
                }
            }

            System.out.print("\n\r");
        }
    }

    // returns if a given cell in the map is walkable or not
    public boolean canGo(int row, int col) {
	return grid[row].charAt(col) != '#';

    }


}
