/**
 * This class represents a single <b>Item</b>.
 * Every <b>Item</b> has a type, name, weight, value, and strength.
 */
public class Item {
    
    private ItemType type;

    private String name;

    private int weight;

    private int value;

    private int strength;

	/**
	 * Constructs a new <b>Item</b>.
	 * @param type the <b>ItemType</b> value of the item.
	 * @param name the name of the item.
	 * @param weight how much the item weighs.
	 * @param value how much the item is worth for buying/selling.
	 * @param strength the item strength, which varies by type
	 * (damage for weapons, protection for armor).
	 */
    public Item(ItemType type, String name, int weight, int value, int strength) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.strength = strength;
    }
	
	/**
	 * Gets the <b>weight</b> of the item.
	 *
	 * @return weight of the item.
	 */
    public int getWeight() {
        return weight;
    }
	
	/**
	 * Gets the <b>value</b> of the item.
	 *
	 * @return value of the item.
	 */
    public int getValue() {
        return value;
    }

	/**
	 * Gets the <b>strength</b> of the item.
	 *
	 * @return strength of the item.
	 */
    public int getStrength() {
        return strength;
    }

	/**
	 * Gets the <b>name</b> of the item.
	 *
	 * @return name of the item.
	 */
    public String getName() {
        return name;
    }

	/**
	 * Gets the <b>type</b> of the item.
	 *
	 * @return type of the item.
	 */
    public ItemType getType() {
        return type;
    }

    @Override
	/**
	 * Returns a string representation of an object, <b>Item</b>.
	 * (The default toString() method in Object prints
	 * "class name @ hash code", we override it to get proper output).
	 *
	 * @return a string representation of an <b>Item</b>.
	 */
    public String toString() {
        return name + " " + weight + " " + value + " " + strength;
    }
}

