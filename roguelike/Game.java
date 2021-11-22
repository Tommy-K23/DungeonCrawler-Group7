// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;

public class Game {
        private Room room;
        private Player player;
        private ArrayList<Box> boxes;
        private ArrayList<Enemy> enemies;
        private ArrayList<Teleporter> teleporters;
        private ArrayList<Enemy> boss;
        private World world;
        Scanner in = new Scanner(new File ("save.txt"));

        public Game() {
                world = new World();
                room = world.getCurrentRoom();
                player = new Player(room.getPlayerStart());
                boxes = room.getBoxes();
                enemies = room.getEnemies();
                teleporters = room.getTeleporters();
                boss = room.getBoss();
                world = new World();
        }

        // prints a help menu to the left of the map
        private void showHelp() {
                String[] cmds = {"Commands:",
                        "---------",
                        "Save: k",
                        "Move: WASD",
                        "Use: f",
                        "Pickup an item: e",
                        "Drop an item: q",
                        "List items: i",
                        "Equip weapon: r",
                        "Equip armor: t",
                        "Quit: ESC"
                };
                Terminal.setForeground(Color.GREEN);
                for (int row = 0; row < cmds.length; row++) {
                        Terminal.warpCursor(row + 1, room.getCols());
                        System.out.print(cmds[row]);
                }
                Terminal.reset();
        }

        //Saves the game to a file, "save.txt"
        public void save() {
            try { 
                File save = new File ("save.txt");
                PrintWriter pw = new PrintWriter(save);
                world.save(pw);//calls world save, which calls rooms 1,2, and 3, to save. This in turn should save each of their respective boxes, and enemies. Player.save should be called separately.
                player.save(pw);

                pw.close();//MUST CLOSE FILE PROPERLY WHEN DONE
            } catch (FileNotFoundException e) {
                System.out.print("Could not load save file.\n\r");
            }
        }

        public Game (Scanner in)//Load the game from a text save file, NEEDS A TRY CATCH BLOCK.
	    {
        try{
        world = new World(in);
        Player player = new Player(in);
        Room r = world.getCurrentRoom();
        boxes = world.getCurrentRoom().getBoxes();
        enemies = r.getEnemies();
        teleporters = room.getTeleporters();

        in.close();//MUST CLOSE FILE PROPERLY WHEN DONE
        }

        catch(FileNotFoundException e)
        {
        System.out.print("Could not Find save file.");
        }
        }




        // right under the map we keep a line for status messages
        private void setStatus(String mesg) {
                // clear anything old first
                Terminal.warpCursor(room.getRows(), 0);
                for (int i = 0; i < 100; i++) {
                        System.out.print(" ");
                }

                // then print the message
                Terminal.warpCursor(room.getRows(), 0);
                System.out.print(mesg);
        }

        // code for when the player tries to pickup an item
        private void pickup() {
                Box thing = checkForBox();
                if (thing == null) {
                        setStatus("There is nothing here to pick up...");
                        Terminal.pause(1.25);
                } else {
                        if (player.getInventory().add(thing.getItem())) {
                                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                                boxes.remove(thing);
                        } else {
                                setStatus("This is too large for you to add!");
                        }
                        Terminal.pause(1.25);
                }
        }

        private void teleport() {
	     Teleporter thing = checkForTeleport();
	     if (thing == null) 
	     {     setStatus("There's nothing to use here...");
		   Terminal.pause(1.25); }
	     else {
		  world.changeRoom(teleporters.getEndRoom());
	     }
	}
 
        // code for when the player tries to drop an item
        private void drop() {
                if (checkForBox() == null) {
                        Item dropped = player.getInventory().drop();
                        if (dropped != null) {
                                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
                        }
                        redrawMapAndHelp();
                } else {
                        setStatus("You cannot drop something on an existing item...");
                        Terminal.pause(1.25);
                }
        }

        // handle the key which was read - return false if we quit the game
        private boolean handleKey(Key key) {
                switch (key) {
                        case k:
                                save();
                                break;
                        case e:
                                pickup();
                                break;

                        case i:
                                player.getInventory().print();
                                redrawMapAndHelp();
                                break;

                        case q:
                                drop();
                                break;

                        case r:
                                player.getInventory().equipWeapon();
                                redrawMapAndHelp();
                                break;

                        case t:
                                player.getInventory().equipArmor();
                                redrawMapAndHelp();
                                break;
                        case f:
                  		teleport();              
                                redrawMapAndHelp();
				break;

                                // handle movement
                        case a: player.move(0, -1, room);
                                break;
                        case d: player.move(0, 1, room);
                                break;
                        case w: player.move(-1, 0, room);
                                break;
                        case s: player.move(1, 0, room);
                                break;

                                // and finally the quit command
                        case ESCAPE:
                                return false;
                }

                return true;
        }

        // this is called when we need to redraw the room and help menu
        // this happens after going into a menu like for choosing items
        private void redrawMapAndHelp() {
                room.draw();
                showHelp();
        }

        // returns a Box if the player is on it -- otherwise null
        private Box checkForBox() {
                Position playerLocation = player.getPosition();

                for (Box box : boxes) {
                        if (playerLocation.equals(box.getPosition())) {
                                return box;
                        }
                }

                return null;
        }

        private Teleporter checkForTeleport()
        {
                Position playerLocation = player.getPosition();

                for (Teleporter t : teleporters){
                        if (playerLocation.equals(t.getPosition()))
                        {return t;
                        }
                }
                return null;

        }
        // check for battles and return false if player has died
        private boolean checkBattles() {
                Position playerLocation = player.getPosition();

                // look for an enemy that is close
                Enemy opponent = null;
                for (Enemy enemy : enemies) {
                        if (playerLocation.isAdjacent(enemy.getPosition())) {
                                opponent = enemy;
                        }
                }

                // now do the battle
                if (opponent != null) {
                        opponent.setBattleActive();
                        return player.fight(opponent, room, enemies);
                }

                return true;
        }


        public void run() {
                // draw these for the first time now
                redrawMapAndHelp();

                boolean playing = true;
                while (playing) {
                        // draw the entities
                        for (Box box : boxes) {
                                box.draw();
                        }
                        for (Enemy enemy : enemies) {
                                enemy.draw();
                        }
                        player.draw();

                        // read a key from the user
                        Terminal.warpCursor(room.getRows() + 1, 0);
                        Key key = Terminal.getKey();
                        playing = handleKey(key);

                        // clear status by default
                        setStatus("");

                        // move the enemies
                        for (Enemy enemy : enemies) {
                                enemy.walk(room);
                        }

                        // check for battles
                        if (checkBattles() == false) {
                                setStatus("You have been killed :(\n\r");
                                playing = false;
                        }

                        // check if we are on a box and print what's in it
                        Box thingHere = checkForBox();
                        if (thingHere != null) {
                                setStatus("Here you find: " + thingHere.getItem().getName());
                        }
                }
        }
}

