/**
 * The <b>Player</b> is the "main character", the one that plays the game.
 * Player will start at a fixed position. Players have access to one <b>Inventory</b>.
 * Player will start with three items in their inventory, one of each <b>ItemType</b>.
 */

import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class Player extends Character {
    private Inventory items;
    protected String name;
    protected String profession;
   
   	/**
	 * Constructor for the <b>Player</b>. Gives an inventory and items
	 * to the player.
	 * @param start the <b>Position</b> that the player starts on.
	 */
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
	/**
	 * Saves the <b>Player</b> into the save file.
	 * @param pw the <b>PrintWriter</b> that writes the data into the save file.
	 */
    public void save(PrintWriter pw)
    {
        super.save(pw);
        pw.println(name);
        pw.println(profession);
        
        int numItems = items.getNumItems();
        pw.println(numItems);
        for (int i = 0; i<numItems; i++)
        {
            pw.println(items.getItem(i).getType());
            pw.println(items.getItem(i).getName());
            pw.println(items.getItem(i).getWeight());
            pw.println(items.getItem(i).getValue());
            pw.println(items.getItem(i).getStrength());
        }

    }

	/**
	 * Loads the <b>Player</b> from the save file.
	 * @param in the Scanner that reads data from the save file.
	 */
    public Player(Scanner in) 
    {
	super(in, Color.CYAN);
    in.nextLine();
    this.name=in.nextLine();
    this.profession=in.nextLine();
    int numItems=in.nextInt();
    Terminal.pause(2);

    items = new Inventory(100);
    for (int i=0;i<numItems;i++)
        {
        String itypehold = in.next();
        ItemType itype= ItemType.valueOf(itypehold);
        in.nextLine();
        String iname=in.nextLine();
        int iweight = in.nextInt();
        int ivalue = in.nextInt();
        int istrength=in.nextInt();
        items.add(new Item(itype,iname,iweight,ivalue,istrength));
        }

    }

    @Override
	/**
	 * Gets the amount of damage that the <b>Player</b> does.
	 * 
	 * @return the player's damage.
	 */
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

	/**
	 * Sets the player's name.
	 */
    public void setName(String name) {
        this.name = name;
    }

    @Override
	/**
	 * Gets the player's name.
	 *
	 * @return the player's name.
	 */
    public String getName() {
        return name;
    }

	/**
	 * Sets the player's profession.
	 */
    public void setProfession(String profession) {
        this.profession = profession;
    }

	/**
	 * Gets the player's profession.
	 *
	 * @return the player's profession.
	 */
    public String getProfession() {
        return profession;
    }

    @Override
	/**
	 * Gets the player's protection from damage.
	 *
	 * @return player's protection from damage.
	 */
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }

	/**
	 * Gets the player's <b>Inventory</b>.
	 *
	 * @return the items in the player's inventory.
	 */
    public Inventory getInventory() {
        return items;
    }
}

