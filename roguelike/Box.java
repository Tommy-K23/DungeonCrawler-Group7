// Box.java
// represents a pickup-able item

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class Box extends Entity {
        // the Item that is in the box
        private Item item;

        // add a box with a given item in it
        public Box(int row, int col, Item item) {
                super(row, col, '^', Color.MAGENTA);
                this.item = item;
        }

                
        public Box(Scanner in)//load
        {
                super(in);
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
        public void save(PrintWriter pw)//save
        {
                super.save(pw);
                pw.println(item.getType());
                pw.println(item.getName());
                pw.println(item.getWeight());
                pw.println(item.getValue());
                pw.println(item.getStrength());
        }   



        public Item getItem() {
                return item;
        }
}


