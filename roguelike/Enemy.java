import java.util.Random;   
import ansi_terminal.*;    
import java.util.Scanner;  
import java.io.PrintWriter;

/**
 * Holds information for <b>Enemy Character</b>s. All enemies have a
 * name. row and column positions, hp (health points), damage points, and
 * protection points. As a type of <b>Character</b>, they also have display
 * characters and colors.
 *
 */
public class Enemy extends Character {
        private String name;
        private int damage;
        private int protection;
        private static Random rng;
        private boolean battleActive;
		
		/**
		 * Constructor for enemies.
		 * @param name the enemy's name.
		 * @param row the row position of the enemy.
		 * @param col the column position of the enemy.
		 * @param hp how many health points the enemy has.
		 * @param damage how many points of damage the enemy does.
		 * @param protection how much protection the enemy has from damage.
		 */
        public Enemy(String name, int row, int col, int hp, int damage, int protection) {
                super(row, col, '%', Color.RED, hp);
                this.name = name;
                this.damage = damage;
                this.protection = protection;
                this.battleActive = false;
                rng = new Random();
        }
	
        @Override
		/**
		 * Gets how much damage the <b>Enemy</b> does.
		 *
		 * @return the amount of damage points.
		 */
                public int getDamage() {
                        return damage;
                }

		/**
		 * This saves the <b>Enemy</b> into the save file.
		 * @param pw a <b>PrintWriter</b> that is used to write the data into the save file.
		 */
        @Override
        public void save(PrintWriter pw)
        {
                super.save(pw);
                pw.println(name);
                pw.println(damage);
                pw.println(protection);
        }

		/**
		 * This loads the <b>Enemy</b> from the save file.
		 * @param in the Scanner that reads data from the save file.
		 */
        public Enemy (Scanner in)
        {
                super (in , Color.RED);
                this.name=in.nextLine();
		        in.nextLine();
                this.damage=in.nextInt();
                this.protection = in.nextInt();
                this.battleActive = false;
                rng= new Random();
        }
        @Override
		/**
		 * Gets the enemy's amount of protection from damage.
		 *
		 * @return the enemy's protection points.
		 */
                public int getProtection() {
                        return protection;
                }

        @Override
		/**
		 * Gets the enemy's name.
		 *
		 * @return the enemy's name.
		 */
                public String getName() {
                        return name;
                }

		/**
		 * Sets <b>battleActive</b> to true.
		 * This indicates that there is currently a battle happening.
		 */
        public void setBattleActive() {
                battleActive = true;
        }

        /**
		 * Makes the <b>Enemy</b> move randomly throughout the room.
		 * Cannot move through walls or while in battle.
		 * @param room the room the enemy is in.
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


