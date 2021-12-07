/**
 * A <b>Character</b> is a type of Entity. This class allows for battles 
 * between characters (<b>Enemy</b> vs <b>Player</b>), see {@link
 * Character#dealDamage} and {@link Character#fight}.
 *
 */

import java.util.ArrayList;

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public abstract class Character extends Entity {
        // the characters health points
        private int hp;

		/**
		 * A constructor for <b>Character</b>s.
		 * @param row their row position.
		 * @param col their column position.
		 * @param display their display character.
		 * @param color the color of their display character.
		 * @param hp health points.
		 */
        public Character(int row, int col, char display, Color color, int hp) {
                super(row, col, display, color);
                this.hp = hp;
        }

        /**
		 * Get the health of the <b>Character</b>.
		 *
		 * @return hp of the character.
		 */
        public int getHealth() {
                return hp;
        }

		/**
		 * Gets the amount of damage the <b>Character</b> does.
		 *
		 * @return the damage the character does.
		 */
        public abstract int getDamage();

		/**
		 * Gets the protection the <b>Character</b> has.
		 *
		 * @return protection.
		 */
        public abstract int getProtection();

		/**
		 * Gets the <b>Character</b>'s name.
		 *
		 * @return the character's name.
		 */
        public abstract String getName();

        /**
		 * Do damage in a battle to another <b>Character</b>.
		 * @param other a character in battle.
		 * @param room the current room.
		 *
		 * @return true if the character has been killed.
		 */
        private boolean dealDamage(Character other, Room room) {
                // this character does damage to the other character
                int damageDone = getDamage() - other.getProtection();

                // prevent negative damage
                if (damageDone < 0) {
                        damageDone = 0;
                }

                // actually damage them
                other.hp -= damageDone;

                // prevent negative hp
                if (other.hp < 0) {
                        other.hp = 0;
                }

                // print the info on this
                Terminal.warpCursor(room.getRows(), 0);
                if (other.hp > 0) {
                        System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                                        + ", leaving " + other.hp + " health.\n\r");
                        return false;
                } else {
                        System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                                        + ", killing them.\n\r");
                        return true;
                }
        }
        @Override
		/**
		 * Saves the <b>Character</b> to the save file.
		 * @param pw the <b>PrintWriter</b> that writes data to the save file.
		 */
        public void save (PrintWriter pw)//Save
        {
                super.save(pw);
                pw.println(hp);
        }

		/**
		 * Loads the <b>Character</b> frm the save file,
		 * @param in the Scanner that reads from the save file.
		 * @param color the character's color.
		 */
	public Character(Scanner in, Color color)//load
	{
		super(in , color);
		hp=in.nextInt();
    }   


    /**
	 * Performs a round of battle between two <b>Character</b>s.
	 * @param other a character in the battle.
	 * @param room the current room.
	 * @param enemies an <b>ArrayList</b> of enemies.
	 *
	 * @return false if a character in battle has died.
	 */
    public boolean fight(Character other, Room room, ArrayList<Enemy> enemies) {
        // do damage to them first
        boolean killed = dealDamage(other, room);
        if (killed) {
            enemies.remove(other);
	}
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();

        // don't allow dead enemies to fight back
        if (killed) {
               return true;
        }

        // now take damage from them
        if (other.dealDamage(this, room)) {
                 return false;
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();
        return true;
        }
   }

