// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;

public class EnemyGenerator {
    public static Enemy generate(int row, int col) {
        // TODO: replace this with your own code!
        Random numgen = new Random();
        int enemycount = 4; //how many unique enemy types we have
        int EnemyID = (1+numgen.nextInt(enemycount)); //generate a random int between 1 and enemycount+1
        if (EnemyID==1)
        {
        return new Enemy("Pitiful Chimera", row, col, 15, 3, 1);
        }

        if (EnemyID==2)
        {
        return new Enemy("Chitinous Abberation", row, col, 30,3,6);
        }

        if(EnemyID==3)
        {
        return new Enemy("Meat Crab",row,col,20,4,4);
        }

        if (EnemyID==4)
        {
        return new Enemy("Bladed Mistake",row,col,10,7,2);
        }
    }
}

