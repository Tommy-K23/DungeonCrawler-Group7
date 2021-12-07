import ansi_terminal.*;

/**
 * <b>Teleporter</b>s allow you to move from one room to another.
 * Every teleporter must start and end somewhere.
 *
 */
public class Teleporter extends Entity{

int start = 0;
int end = 0;

/**
 * Constructor for <b>Teleporter</b>s.
 * @param start the room that the teleporter is located.
 * @param end the room that the teleporter takes the player.
 * @param row the row that the display character is located.
 * @param col the column that the display character is located.
 */
public Teleporter(int start, int end, int row, int col)
{
super(row, col, '*', Color.MAGENTA);
this.start=start;
this.end=end;
}

/**
 * Gets the starting room of the <b>Teleporter</b>.
 *
 * @return the start room.
 */
public int getStartRoom()//just here to be useful.
{return start;}

/**
 * Gets the ending room of the <b>Teleporter</b>.
 *
 * @return the end room.
 */
public int getEndRoom()//use this in conjunction with the "changeRoom()" method to teleport.
{return end;}

}
