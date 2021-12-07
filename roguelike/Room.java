/**
*Provides code for the drawing of a room
*Also provides starting locations for the player, boxes, and enemies
*Holds the geometry of a Room, as well as the size of the room.
*Also holds all entities in the Room.
*/

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
        int roomNum = 1;
        boolean load;
        protected ArrayList<Box> boxes;
        protected ArrayList<Enemy> enemies;
        protected ArrayList<Boss> boss;
/**
*Standard constructor for creating the room. Used when making a new Game.
*@param grid[] is a set of Strings that represents the physical space of a Room, or level.
*@param rows is an integer that is the number of rows in the grid.
*@param cols is an integer that is the number of columns in the grid.
*/
        public Room(String grid[], int rows, int cols) {
                this.grid = grid;
                this.rows = rows;
                this.cols = cols;
                load = false; 

        }
/**
*Method that takes in a PrintWriter object and uses it to save all entities stored in the room.
*@param pw is the name assigned to the PrintWriter object.
*/
        public void save(PrintWriter pw)
        {
                // we need to save boxes and enemies.
                pw.println("Saving room below.");
                int boxArraySize = boxes.size();
                pw.println(boxArraySize);
                for (Box box : boxes)
                {box.save(pw);}

                int enemyArraySize=enemies.size();
                pw.println(enemyArraySize);
                for (Enemy enemy : enemies)
                {enemy.save(pw);}


                int bossArraySize=boss.size();
                pw.println(bossArraySize);
                for (Boss boss : boss)
                {boss.save(pw);}
        }
/**
*Constructor that is used for Room when the game is being loaded from a file.
*The only difference between this constructor and the standard one, is that it also fills ArrayLists with entities from the save file.
*@param grid[] is a set of Strings that represents the physical space of a Room, or level.
*@param rows is an integet that is the number of rows in the grid.
*@param cols is an integer that is the number of columns in the grid.
*/
        public Room (String grid[], int rows, int cols, Scanner in)
        {
                load=true;
                this.grid = grid;
                this.rows=rows;
                this.cols=cols;
                in.nextLine();//skips the first line in save.txt
		        in.nextLine();//skips the second line in save.txt
                int boxArraySize = in.nextInt();
                boxes = new ArrayList<Box>();
                for (int a = 0; a < boxArraySize; a++)
                {
                        Box currentLoadBox = new Box(in);
                        boxes.add(currentLoadBox);
                }
                int enemyArraySize=in.nextInt();
                enemies = new ArrayList<Enemy>();
                for (int b = 0; b < enemyArraySize;b++)
                {
                        Enemy currentLoadEnemy = new Enemy(in);
                        enemies.add(currentLoadEnemy);
                }

                int bossArraySize = in.nextInt();
                boss = new ArrayList<Boss>();
                for (int c = 0; c < bossArraySize; c++)
                {
                        Boss currentLoadBoss = new Boss(in);
                        boss.add(currentLoadBoss);
                }
        }
        /**
        *Method that searches for the maps predetermined player start position, and sets the player there.
        *@returns the Player Position in this room
        */
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
        /**
        *creates a new set of Boxes if this is the first time the room is being loaded. Otherwise, returns an ArrayList with at least one Dummy Box, which is meant to eliminate NullPointerErrors.
        *@returns a set of item boxes for this map, this is here because it depends on the room geometry for where the boxes make sense to be
        */
        public ArrayList<Box> getBoxes() {

                if(boxes==null)
                {
                        boxes = new ArrayList<Box>();
                        for (int row = 0; row < rows; row++) {
                                for (int col = 0; col < cols; col++) {
                                        if (grid[row].charAt(col) == '^') {
                                                boxes.add(new Box(row, col, ItemGenerator.generate()));
                                        }                                
				}

                        }
		if(boxes.size() == 0){
		boxes.add(new Box(6, 10, ItemGenerator.generate()));
		}
                }

                return boxes;
        }
        /**
        *creates an ArrayList for where a Teleporter, or several may be stored.
        *@returns a set of teleporters for each map
        */
        public ArrayList<Teleporter> getTeleporters() {

                ArrayList<Teleporter> teleporters = new ArrayList<Teleporter>();
                for (int row = 0; row < rows; row++) {
                        for (int col = 0; col < cols; col++) {
                                if (grid[row].charAt(col) == '*') {
                                        teleporters.add(new Teleporter(roomNum, roomNum + 1, row, col));

                                }
                        }
                }
                return teleporters;
        }
        /**
        *Creates or returns an ArrayList containing a Room's enemies.
        *@returns a set of enemies from this map.
        */
        public ArrayList<Enemy> getEnemies() {

                if(enemies==null)
                {
                        enemies = new ArrayList<Enemy>();
                        for (int row = 0; row < rows; row++) {
                                for (int col = 0; col < cols; col++) {
                                        if (grid[row].charAt(col) == '%') {
                                                enemies.add(EnemyGenerator.generate(row, col));
                                        }
                                }
                        }
                }


                return enemies;
        }
        /**
        *Creates an Array with a Boss inside and returns that Array.
        *@returns a boss for the final Room
        */
        public ArrayList<Boss> getBoss() {
                if(boss==null)
                {
                        boss = new ArrayList<Boss>();
                        for (int row = 0; row <rows; row++){
                                for (int col = 0; col < cols; col++){
                                        if (grid[row].charAt(col) == '&'){
                                                Boss chickenBoss=new Boss("Chicken Chimera",row,col,40,10,15);
                                                boss.add(chickenBoss);
                                        }
                                }
                        }
                }
                
                return boss; 
        }
        /**
        *Getter for the Room's number of rows.
        *@returns an integer
        */
        public int getRows() {
                return rows;
        }
        /**
        *Getter for the Room's number of columns.
        *@returns an integer
        */
        public int getCols() {
                return cols;
        }

        /**
        *draws the map to the screen
        */
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
        /**
        *Checks to see whether a gridspace can be walked upon in a Room.
        *Vital for keeping Entities inside of the Room.
        *@param row is the integer value for the row being checked.
        *@param col is the integer value for the column being checked.
        *@returns if a given cell in the map is walkable or not
        */
        public boolean canGo(int row, int col) {
                return grid[row].charAt(col) != '#';

        }


}
