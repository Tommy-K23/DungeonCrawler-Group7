// Player.java

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class Player extends Character {
    private Inventory items;
    private String name;
    private String profession;
    
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

    public Player(Scanner in) 
    {
	super(in);
	Terminal.warpCursor(2,100);
	System.out.print("6");
	Terminal.pause(2);
    name=in.nextLine();
    int numItems=items.getNumItems();
    items = new Inventory(100);
    for (int i=0;i<numItems;i++)
        {
        ItemType itype= ItemType.valueOf(in.next());
        String iname=in.nextLine();
        int iweight = in.nextInt();
        int ivalue = in.nextInt();
        int istrength=in.nextInt();
        items.add(new Item(itype,iname,iweight,ivalue,istrength));
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
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

