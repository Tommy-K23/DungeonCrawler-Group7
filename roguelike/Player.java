// Player.java

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class Player extends Character {
    private Inventory items;

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
    }
    @Override
    public void save(PrintWriter pw)
    {
    super.save(pw);
    pw.println(name);
    for (Item i : items)
    {
    pw.println(i.getType());
    pw.println(i.getName());
    pw.println(i.getWeight());
    pw.println(i.getValue());
    pw.println(i.getStrength());
    }

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
        return "Player";
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

