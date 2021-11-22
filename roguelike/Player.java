// Player.java

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class Player extends Character {
    private Inventory items;
    private static String name;

    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);
    
        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        //Item(name,weight,value,strength)
        items.addAndEquip(new Item(ItemType.Weapon, "Broken Beaker", 5, 5, 7));
        items.addAndEquip(new Item(ItemType.Armor, "Stained Linens", 10, 10, 3));
        items.add(new Item(ItemType.Other,"Overpriced Tech", 8, 200, 0));
        Terminal.warpCursor(55, 0);
        String name = Terminal.getLine("What is your name? ");
        Terminal.warpCursor(55, 0);
        String profession = Terminal.getLine("What is your profession? ");
        Terminal.warpCursor(55, 0);
    }
    @Override
    public void save(PrintWriter pw)
    {
        super.save(pw);
        pw.println(getName());
        
        int numItems = items.getNumItems();

        for (int i = 0; i<numItems; i++)
        {
            pw.println(items.getItem(i).getType());
            pw.println(items.getItem(i).getName());
            pw.println(items.getItem(i).getWeight());
            pw.println(items.getItem(i).getValue());
            pw.println(items.getItem(i).getStrength());
        }

    }

    public void Player(Scanner in) 
    {
	//compiler issue in Game: 66 
    }

    @Override
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }

    public Inventory getInventory() {
        return items;
    }
}

