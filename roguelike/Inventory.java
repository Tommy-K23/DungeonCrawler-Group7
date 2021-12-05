/**
 * This class acts as an <b>Item</b> storage system for the <b>Player</b>.
 * The player can equip weapons and armor, add new items,
 * drop items and view their <b>Inventory</b>.
 * Players can only have one inventory, and each inventory has a
 * maximum carry weight.
 */
import java.util.ArrayList;
import java.util.Scanner;

import ansi_terminal.*;

public class Inventory {
    // the actual list of items
    private ArrayList<Item> items;

    // which item is equipped, if any
    private Item equippedArmor;
    private Item equippedWeapon;

    // the max weight limit for the player here
    private int maxWeight;

	/**
	 * Constructor for a new <b>Inventory</b>.
	 * @param maxWeight the maximum weight the inventory can hold.
	 */
    public Inventory(int maxWeight) {
        items = new ArrayList<Item>();
        this.maxWeight = maxWeight;
    }

    /**
	 * This adds a new <b>Item</b> to the <b>Inventory</b>.
	 * New items will not be added if the item will cause the
	 * inventory's weight to exceed the maximum allowance.
	 * @param item the item that is being added.
	 *
	 * @return true if the item was added successfully and
	 * false if not.
	 */
    public boolean add(Item item) {
        if ((item.getWeight() + totalWeight()) > maxWeight) {
            return false;
        } else {
            items.add(item);
            return true;
        }
    }

    /**
	 * In addition to adding an <b>Item</b> to the inventory, if it is of type
	 * Weapon or Armor, it will equip it into the proper "slot".
	 * @param item the item that is being added.
	 */
    public void addAndEquip(Item item) {
        items.add(item);

        if (item.getType() == ItemType.Weapon) {
            equippedWeapon = item;
        } else if (item.getType() == ItemType.Armor) {
            equippedArmor = item;
        }
    }

    /**
	 * Gets the <b>equippedWeapon</b>.
	 *
	 * @return the equipped weapon.
	 */
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }
	
	/**
	 * Gets the <b>equippedArmor</b>.
	 *
	 * @return the equipped armor.
	 */
    public Item getEquippedArmor() {
        return equippedArmor;
    }

    /**
	 * Gets the accumulated weight of all of the items in the <b>Inventory</b>.
	 * 
	 * @return the total weight of the inventory.
	 */
    public int totalWeight() {
        int total = 0;
        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

	/**
	 * Prints all of the items in the <b>Inventory</b> that match the given <b>ItemType</b>
	 * (can be null).
	 * @param filter the type of an item.
	 *
	 * @return the number of items matching the type.
	 */
    private int print(ItemType filter) {
        // clear the terminal so we print over all else
        Terminal.clear();

        // print a heading row
        // the numbers and junk are to make it print in nice columns
        Terminal.setForeground(Color.RED);
        System.out.printf("%-4s %-40s %-8s %-8s %-8s\n\r", "No.", "Name", "Weight", "Value", "Strength");
        Terminal.reset();

        // print each item out
        int num = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                System.out.printf("%-4d %-40s %-8s %-8s %-8s", num + 1, i.getName(), i.getWeight(), i.getValue(), i.getStrength());

                // tell them if this thing is equipped
                if (i == equippedArmor) {
                    System.out.print(" (equipped armor)");
                } else if (i == equippedWeapon) {
                    System.out.print(" (equipped weapon)");
                }
                System.out.print("\n\r");

                num++;
            }
        }

        return num;
    }

    /**
	 * Will make the player stay on the current screen until a key press.
	 */
    public void pressAnyKey() {
        System.out.printf("\n\rPress any key to return...\n\r");
        Terminal.getKey();
    }

    /**
	 * Prints all of the items in the <b>Inventory</b>.
	 */
    public void print() {
        print(null);
        pressAnyKey();
    }

    /**
	 * Allows the player to drop an item from their <b>Inventory</b>.
	 *
	 * @return the item the player wishes to drop.
	 */
    public Item drop() {
        Item toDrop = pickItem(null);
        if (toDrop != null) {
            // if we're dropping our equipped stuff, remove those too!
            if (equippedWeapon == toDrop) {
                equippedWeapon = null;
            } else if (equippedArmor == toDrop) {
                equippedArmor = null;
            }

            items.remove(toDrop);
        }

        if (toDrop != null) {
            System.out.print("You dropped the " + toDrop.getName() + "...\n\r");
        } else {
            System.out.print("You dropped nothing...\n\r");
        }

        pressAnyKey();
        return toDrop;
    }

    /**
	 * Allows the player to equip an <b>Item</b>.
	 * @param type the <b>ItemType</b> of an item.
	 *
	 * @return the item the player wishes to equip.
	 */
    private Item equip(ItemType type) {
        Item thing = pickItem(type);
        if (thing != null) {
            System.out.print("You equipped the " + thing.getName() + "\n\r");
        } else {
            System.out.print("You equipped nothing...\n\r");
        }
        pressAnyKey();
        return thing;
    }

    /**
	 * Allows the player to equip an item of type <b>Weapon</b>.
	 */
    public void equipWeapon() {
        equippedWeapon = equip(ItemType.Weapon);
    }

    /**
	 * Allows the player to equip an item of type <b>Armor</b>.
	 */
    public void equipArmor() {
        equippedArmor = equip(ItemType.Armor);
    }

	/**
	 * Allows the player to choose an item from their <b>Inventory</b>.
	 * @param filter the <b>ItemType</b> of an item.
	 *
	 * @return the item chosen.
	 */
    private Item pickItem(ItemType filter) {
        // print all the matching items
        int options = print(filter);

        if (options == 0) {
            System.out.print("You have no appropriate items!\n\r");
            return null;
        }

        // give them a cancel option as well
        System.out.print((options + 1) + "    None\n\r");

        // get their choice, only allowing ints in the correct range
        int choice = 0;
        do {
            String entry = Terminal.getLine("Select an item: ");
            try {
                choice = Integer.parseInt(entry) - 1;
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (choice < 0 || choice > options);

        // go through and skip items until we reach this one
        int realIndex = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                if (choice == 0) {
                    break;
                }
                choice--;
            }
            realIndex++;
        }

        // return the thing they chose
        if (realIndex < 0 || realIndex >= items.size()) {
            return null;
        } else {
            return items.get(realIndex);
        }
    }
	
	/**
	 * Gets the number of items in the <b>Inventory</b>.
	 *
	 * @return the number of items.
	 */
    public int getNumItems() {
        int numItems = 0;
        for (Item i : items) {
            numItems++;
        }
        return numItems;
    }

	/**
	 * Gets an item from the <b>Inventory</b> list.
	 * @param i the index of the item.
	 * 
	 * @return the item indexed from the list.
	 */
    public Item getItem(int i) {
      Item p = items.get(i);
      return p;
    }
}

