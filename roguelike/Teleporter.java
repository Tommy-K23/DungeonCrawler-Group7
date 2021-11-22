public class Teleporter extends Entity{

Room start = null;
Room end = null;

public Teleport(Room start, Room end, int row, int col)//start is the Room where the Teleporter is, and end is where the Teleport brings you.
{
super(row,col,'*',color.BLUE);
this.start=start;
this.end=end;
}

public getPosition()
{return position;}

public getStartRoom()//just here to be useful.
{return start;}

public getEndRoom()//use this in conjunction with the "changeRoom()" method to teleport.
{return end;}

//I don't think (I'm unsure though), whether we really need to save this.


}
