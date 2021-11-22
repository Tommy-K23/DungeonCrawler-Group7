import ansi_terminal.*;

public class Teleporter extends Entity{

int start = 0;
int end = 0;

public Teleporter(int start, int end, int row, int col)//start is the Room where the Teleporter is, and end is where the Teleport brings you.
{
super(row, col, '*', Color.MAGENTA);
this.start=start;
this.end=end;
}


public int getStartRoom()//just here to be useful.
{return start;}

public int getEndRoom()//use this in conjunction with the "changeRoom()" method to teleport.
{return end;}

//I don't think (I'm unsure though), whether we really need to save this.


}
