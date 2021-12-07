/**
 * This class represents an item that is able to be picked up by the <b>Player</b>.
 * "<b>Boxes</b>" are spaces on the grid with items in them.
 */

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class Box extends Entity {
        // the Item that is in the box
        private Item item;

        /**
		 * Adds a box with an <b>Item</b> in it.
		 * @param row the row position of the box.
		 * @param col the column position of the box.
		 * @param item the item that is in the box.
		 */
        public Box(int row, int col, Item item) {
                super(row, col, '^', Color.YELLOW);
                this.item = item;
        }

        /**
		 * Loads the <b>Box</b> with the <b>Item</b> from the save file.
		 * @param in the Scanner that reads data from the save file.
		 */
        public Box(Scanner in)//load
        {
                super(in, Color.YELLOW);
		        String value = in.nextLine();
                String a=in.nextLine();
                int b=in.nextInt();
                int c=in.nextInt();
                int d=in.nextInt();
		switch (value) {
		case "Weapon":
                	this.item = new Item(ItemType.Weapon,a,b,c,d);
			break;
		case "Armor":
			this.item = new Item(ItemType.Armor,a,b,c,d);
			break;
		case "Other":
			this.item = new Item(ItemType.Other,a,b,c,d);
			break;
		}
		
        }

        @Override
		/**
		 * Saves the <b>Box</b> to the save file.
		 * @param pw the <b>PrintWriter</b> that gives the data to the save file.
		 */
        public void save(PrintWriter pw)//save
        {
                super.save(pw);
                pw.println(item.getType());
                pw.println(item.getName());
                pw.println(item.getWeight());
                pw.println(item.getValue());
                pw.println(item.getStrength());
        }   


		/**
		 * Gets the <b>Item</b> from the box.
		 *
		 * @return the item.
		 */
        public Item getItem() {
                return item;
        }
}


