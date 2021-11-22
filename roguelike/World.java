public class World
{
        String grid1 [] = 
        {


        };


        String grid2 [] = 
        {
        

        };
        
        String grid3 [] =
        {


        };

        Room currentRoom = null;

        public World() //Supposed to create the 3 levels, then set the current room to room 1.
        {
                Room r1 = new Room(grid1);

                Room r2 = new Room(grid2);

                Room r3 = new Room(grid3);

                currentRoom = r1;
        }

        public Room getCurrentRoom ()
        {return currentRoom;}

        public void changeRoom(int x)//takes an int as an arguement and translates it to change the currentRoom to its respective level.

        {

                if (x=1)
                {currentRoom=r1;}

                if(x==2)
                {currentRoom=r2;}

                if(x==3)
                {currentRoom=r3;}

        }


        public int getRoomNum()//returns the level number for the current room.
        {
                if (currentRoom==r1)
                {return 1;}

                if(currentRoom==r2)
                {return 2;}

                if (currentRoom==r3)
                {return 3;}

        }

        public void save(Printwriter pw)
        {
        r1.save(pw);
        r2.save(pw);
        r3.save(pw);
        }


        public World (Scanner in)
        {
        r1 = new Room(in);
        r2 = new Room(in);
        r3 = new Room(in);
        }
}
