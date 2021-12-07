/**
 * This class generates enemies. All enemies have a name, row and
 * column positions, hp (health points), damage points, and protection points.
 * The number of enemies is fixed. Types of enemies and their values are also fixed,
 * but which enemy is generated is random.
 *
 * @author ET
 */
import java.util.Random;

public class EnemyGenerator {

	/**
	 * This method generates an <b>Enemy</b> by picking a random number that
	 * is assigned to an enemy.
	 * @param row the row position on the map.
	 * @param col the column position on the map.
	 *
	 * @return a new <b>Enemy</b>.
	 */
    public static Enemy generate(int row, int col) 
    {

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
return new Enemy("Sad blob",row,col,10,3,3);

    }
}

