// ItemGenerator.java
// this class contains a static method for creating items randomly

import java.util.Random;

public class ItemGenerator {
    public static Item generate() {
        
        String[] weaponNames = {"Broken Beaker", "Rusty Pliers", "Scalpel", "Electric Prod"};
        String[] armorNames = {"Stained Linens", "Lab Coat", "Hazard Suit", "Impact Goggles"};
        String[] otherNames = {"Unidentified Specimen Jar", "DNA Sample", "Overpriced Tech", "Graduated Cylinder"};

        Random rng = new Random();

        ItemType type = ItemType.values()[new Random().nextInt(ItemType.values().length)];

        int weight = rng.nextInt(16);
        int value = rng.nextInt(21);
        int strength = rng.nextInt(11);

        String name = " ";
        int i = 0;

        switch(type) {
            case Weapon:
                i = rng.nextInt(weaponNames.length);
                name = weaponNames[i];
                break;
            case Armor:
                i = rng.nextInt(armorNames.length);
                name = armorNames[i];
                break;
            case Other:
                i = rng.nextInt(otherNames.length);
                name = otherNames[i];
                strength = 0;
                if (name.equals("Overpriced Tech")) {
                    value = 200;
                }
                break;
        }
        return new Item(type, name, weight, value, strength);
    }
}

