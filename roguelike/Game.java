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
        private ArrayList<Boss> boss;
        private World world;
	private String name;
	private String profession;
        PrintWriter pw =null;
        public Game() {
                Terminal.warpCursor(21, 0);
                name = Terminal.getLine("What is your name? ");
                Terminal.warpCursor(21, 0);
                profession = Terminal.getLine("What is your profession? ");
                Terminal.warpCursor(21, 0);	
		world = new World();
 		room = world.getCurrentRoom();
		player = new Player(room.getPlayerStart());
                player.setName(name);
                player.setProfession(profession);
		Terminal.warpCursor(0,0);
		setStatus("You are employee 12721 of Blanket Labs where you're role is: " + profession);
		Terminal.pause(5);
		setStatus("You've been going about your day, until you hear a large BOOM from somewhere far.");
		Terminal.pause(4.3);
		setStatus("You ignore it for a while, not the first time you've heard an explosion in a science lab");
		Terminal.pause(4.5);
		setStatus("But soon you start to hear groaning and screams for help. That's when you see them. The monsters...");
		Terminal.pause(7);
		setPlaces();
		
        }

	private void setPlaces() {        	   
		player.setPosition(room.getPlayerStart());
                boxes = room.getBoxes();
                enemies = room.getEnemies();
                teleporters = room.getTeleporters();
                boss = room.getBoss();
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
                try{
                        if(pw==null)
                        {
                                pw = new PrintWriter("save.txt");
                        }

                        world.save(pw);//calls world save, which calls rooms 1,2, and 3, to save. This in turn should save each of their respective boxes, and enemies. Player.save should be called separately.
                        player.save(pw);

                        pw.close();//MUST CLOSE FILE PROPERLY WHEN DONE
                } catch (FileNotFoundException e) {
                        System.out.print("Could not load save file.\n\r");
                }
        }

        public Game (Scanner in)//Load the game from a text save file
        {

                        world = new World(in);
                        player = new Player(in);
                        room = world.getCurrentRoom();
                        boxes = room.getBoxes();
                        enemies = room.getEnemies();
                        teleporters = room.getTeleporters();

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
                      if (world.getRoomNum() == 1){
		      	setStatus("You press a button in the elevator. Floor B9.");
		      	Terminal.pause(2.25);
		      	world.changeRoom(world.getRoomNum()+1);
		      	room = world.getCurrentRoom();
		      	setPlaces();
		      }else if(world.getRoomNum() == 2){
			setStatus("You pull the only book on the bookshelf to reveal a hidden passage.");
			Terminal.pause(2.25);
			world.changeRoom(world.getRoomNum()+1);
			room = world.getCurrentRoom();
			setPlaces();
			}else{
			setStatus("You find a ladder to a secret tunnel...");
			Terminal.pause(1.5);
			world.changeRoom(world.getRoomNum() + 1);
			Terminal.clear();
			EndScreen end = new EndScreen();
			end.run();
		      }	
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
                                if (world.getRoomNum() < 3){
				teleport();              
                                redrawMapAndHelp();
			        break;
				}else{
				teleport();
				redrawMapAndHelp();
				return false;
				}
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
		Boss bigGuy = null;
                for (Enemy enemy : enemies) {
                        if (playerLocation.isAdjacent(enemy.getPosition())) {
                                opponent = enemy;
                        }
                }
		for (Boss boss : boss) {
			if (playerLocation.isAdjacent(boss.getPosition())) {
				bigGuy = boss;
			}
		}

                // now do the battle
                if (opponent != null) {
                        opponent.setBattleActive();
                        return player.fight(opponent, room, enemies);
                }
		if (bigGuy != null) {
			bigGuy.setBattleActive();
			return player.fight(bigGuy, room, enemies);
		}
                return true;
        }


        public void run() {
                // draw these for the first time now
                redrawMapAndHelp();
                setPlaces();
                boolean playing = true;
                while (playing) {
		    if(world.getRoomNum() < 4){
                        // draw the entities
                        for (Box box : boxes) {
                                box.draw();
                        }
                        for (Enemy enemy : enemies) {
                                enemy.draw();
                        }
			for (Teleporter tp : teleporters){
				tp.draw();
			}
                        player.draw();
			for (Boss a : boss){
				a.draw();
			}
                   }
                        
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
			for (Boss boss : boss) {
				boss.walk(room);
			}
                        // check for battles
                        if (checkBattles() == false) {
				Terminal.warpCursor(0,0);
                                setStatus("You have been killed :(\n\r");
				Terminal.pause(1.25);
                                playing = false;
                        }

                        // check if we are on a box and print what's in it
                        Box thingHere = checkForBox();
                        if (thingHere != null) {
                                setStatus("Here you find: " + thingHere.getItem().getName());
                        }
			Teleporter tpHere = checkForTeleport();
			if (tpHere != null) {
				setStatus("Press f to change rooms.");
			}
		    
                }
        }
}

