/**
 * This class holds information on the <b>Boss</b>.
 * The boss is a type of <b>Character</b>, therefore it also has a name,
 * row position, column position, hp (health points), damage, and protection variables.
 * The <b>Boss</b>, like all other <b>Character</b>s has a display character and color.
 * Even though it is "the boss", it also cannot move through walls.
 *
 * @author TK
 */ 
import java.util.Random;
 import ansi_terminal.*;
 import java.util.Scanner;
 import java.io.PrintWriter;
 public class Boss extends Character {
         private String name;
         private int damage;
         private int protection;
         private static Random rng;
         private boolean battleActive;

		 /**
		  * Constructor for the <b>Boss</b>.
		  * @param name the name of the boss.
		  * @param row the row position of the boss.
		  * @param col the column position of the boss.
		  * @param hp the amount of health points the boss has.
		  * @param damage the amount of damage that the boss does.
		  * @param protection the protection that the boss has from damage.
		  */
         public Boss(String name, int row, int col, int hp, int damage, int protection) {
                 super(row, col, '&', Color.RED, hp);
                 this.name = name;
                 this.damage = damage;
                 this.protection = protection;
                 this.battleActive = false;
                 rng = new Random();
         }

         @Override
		 /**
		  * Gets the damage that the <b>Boss</b> does.
		  *
		  * @return the damage points of the boss.
		  */
                 public int getDamage() {
                         return damage;
                 }

         @Override
		 /**
		  * Saves the <b>Boss</b> into the save file.
		  * @param pw the <b>PrintWriter</b> that saves data into the save file.
		  */
         public void save(PrintWriter pw)
         {
                 super.save(pw);
                 pw.println(name);
                 pw.println(damage);
                 pw.println(protection);
         }

		 /**
		  * Loads the <b>Boss</b> from the save file.
		  * @param in the Scanner that reads from the save file.
		  */
         public Boss (Scanner in)
         {
                 super (in, Color.RED);
                 in.nextLine();
                 name=in.nextLine();
                 this.name=name;
                 
                 damage=in.nextInt();
                 this.damage=damage;
                    
                 protection=in.nextInt();
                 this.protection = protection;

                 this.battleActive = false;
                 rng= new Random();
         }
         @Override
		 /**
		  * Gets the protection the <b>Boss</b> has.
		  *
		  * @return the boss's protection.
		  */
                public int getProtection() {
                         return protection;
                 }
		@Override
		/**
		 * Gets the boss's name.
		 *
		 * @return the boss's name.
		 */
                 public String getName() {
                         return name;
                 }

		/**
		 * Sets <b>battleActive</b> to true.
		 * This indicates that a battle is currently happening.
		 */
         public void setBattleActive() {
                 battleActive = true;
         }

         /**
		  * Moves the <b>Boss</b> randomly throughout the room.
		  * Cannot move through walls or while in battle.
		  * @param room the room the boss is in.
		  */
         public void walk(Room room) {
                 // if a battle is active with this enemy, they DONT walk right after
                 if (battleActive) {
                         battleActive = false;
                         return;
                 }

                 // loop forever until we move correctly
                 while (true) {
                         int choice = rng.nextInt(4);
                         switch (choice) {
                                 case 0:
                                         if (move(0, 1, room)) return;
                                         break;
                                 case 1:
                                         if (move(0, -1, room)) return;
                                         break;
                                 case 2:
                                         if (move(1, 0, room)) return;
                                         break;
                                 case 3:
                                        if (move(-1, 0, room)) return;
                                         break;
                         }
                 }
         }
 }

