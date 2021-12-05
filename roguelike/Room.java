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
        int roomNum = 1;
        boolean load;
        protected ArrayList<Box> boxes;
        protected ArrayList<Enemy> enemies;
        protected ArrayList<Boss> boss;

        public Room(String grid[], int rows, int cols) {
                // this initializes the grid for room one
                this.grid = grid;
                this.rows = rows;
                this.cols = cols;
                load = false; 

        }

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
                }

                return boxes;
        }

        // returns a set of teleporters for each map
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

        // returns a set of enemies from this map, similarly to the boxes above
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
        // returns a boss for the final map
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
