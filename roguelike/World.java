import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class World
{
        String[] grid1 = 
        {
"                       ############                     ",//1
"                       ##      ^ ##                     ",//2
"                       ##        ##                     ",//3
"                       ##        ##                     ",//4
"   ######################        ###################### ",//5
"   ##  @               ##        ##                  ## ",//6
"   ##                  ##        ##                  ## ",//7
"   ##                  ##        ##                  ## ",//8
"   ##                            %                   ## ",//9
"   ######################        ###################### ",//10
"                       ##        ##                     ",//11
"                       ##        ##                     ",//12
"                       ##        ##                     ",//13
"                       ##        ##                     ",//14
"                       ##        ##                     ",//15
"   ######################        ###################### ",//16
"   ##                                                ## ",//17
"   ##                  ##      # ##                  ## ",//18
"   ##        %         #              #              ## ",//19
"   ##^                 ## #      ##                  ## ",//20
"   ######################        ###################### ",//21
"                       ##        ##                     ",//22
"                       ##        ##                     ",//23
"                       ##        ##                     ",//24
"                       ##   %    ##                     ",//25
"                       ##        ##                     ",//26
"                       ##        ##                     ",//27
"                       #####  #####                     ",//28
"                       ## *      ##                     ",//29
"                       ##        ##                     ",//30
"                       ############                     ",//31

        };


        String[] grid2 = 
        {
"       #############################                                               ",
"       ##          |_*__|         ##                                               ",
"       ##                         ##                                               ",
"       ##                         ##                                               ",
"       ##                         ##                                               ",
"       ##       ___  .            ##                                               ",
"       ##      |   |. .. .        ######################################           ",
"       ##     h|   |%..                                               ##           ",
"       ##      |___|..            ################################    ##           ",
"       ##        ^                ##                            ##    ##           ",
"       ##                         ##                            ##    ##           ",
"       ##                         ##                            ##    ##           ",
"       #############################                            ##    ##           ",
"                                                                ##    ##           ",        
"               ###################################################    ####         ",
"               ##                                                   %   ##         ",
"        #########    ##################        ###################    ###########  ",
"        ##           ##              ##        ##               ##             ##  ",
"        ##    %    ^ ##              ##        ##               ##     ^       ##  ",
"        ##           ##              ##        ##               ##             ##  ",
"        ##           ##              ##   @    ##               ##             ##  ",  
"        ###############              #####  #####               #################  ",
"                                     ##        ##                                  ",
"                                     ##        ##                                  ",
"                                     ############                                  ",        

        };
        
        String[] grid3 =
        {

"   #############################                                                          ",
"   ##          |___|          ##                                                          ",
"   ##                         ##                                                          ",
"   ##           @     __o..   ##                                                          ",
"   ##               ^  /|..   ##                                                          ",
"   ##                 /|....  ##                                                          ",
"   ##                         ##       ################################################   ",
"   ##                         ##       ##                                            ##   ",
"   ###########    ##############       ##                                            ##   ",
"            ##    ##                   ##         ___         ___         ___        ##   ",
"            ##    ##                   ##        |   |       |   |       |   |       ##   ",
"            ##    ##                   ##        |   |       |   |       |   |       ##   ",
"            ##    ##                   ##    %   |___|       |___|       |___|       ##   ",
"            ##    ##                   ##                                            ##   ",
"            ##    #######################                                            ##   ",
"            ##                                                                 &     ##   ",
"            #############################                                            ##   ",
"                                       ##    %    ___         ___         ___        ##   ",
"                                       ##        |   |       |   |       |   |       ##   ",
"                                       ##        |   |       |   |       |   |       ##   ",
"                                       ##        |___|       |___|       |___|       ##   ",
"                                       ##                        #                   ##   ",
"                                       ##                                #           ##   ",
"                                       ##                           #                ##   ",
"                                       ##########################---###################   ",
"                                                               ##   ##                    ",
"                                                               ## * ##                    ",
"                                                               #######                    ",
        };



        static Room currentRoom = null;
	    static int roomNum = 1;
	    static Room r1;
            static Room r2;
	    static Room r3;
	    static Room r4;
	    int rows;
	    int cols;
        public World() //Supposed to create the 3 levels, then set the current room to room 1.
        {
                r1 = new Room(grid1, 31, 56);

                r2 = new Room(grid2, 25, 83);

                r3 = new Room(grid3, 28, 90);
	
                currentRoom = r1;
        }

        public Room getCurrentRoom ()
        {return currentRoom;}


        public void changeRoom(int x)//takes an int as an arguement and translates it to change the currentRoom to its respective level.

        {

                if (x==1)
                {currentRoom=r1;
		 roomNum = 1;
		}

                if(x==2)
                {currentRoom=r2;
		 roomNum = 2;
		}

                if(x==3)
                {currentRoom=r3;
		 roomNum = 3;
		}
		
		if (x>=4)
		{currentRoom = r1;	 
	   	 roomNum = 1;

		}

        }


        public static int getRoomNum()//returns the level number for the current room.
        {
        return roomNum;
        }

	public int getRows()
	{return rows;}

	public int getCols()
	{return cols;}


        public void save(PrintWriter pw)
        {
        pw.println(getRoomNum());
	switch (getRoomNum()){
	case 1:
        	r1.save(pw);
		break;
	case 2:
        	r2.save(pw);
		break;
	case 3:
	        r3.save(pw);
        	break;
	}
	}

        public World (Scanner in)
        {

       int rnum=in.nextInt();
       changeRoom(rnum);
       if(rnum==1)
       {
       Room r1 = new Room(grid1 , 31 , 57 , in);
       Room r2 = new Room(grid2 , 24 , 84);
       Room r3 = new Room(grid3 , 28 , 91);
       }
       if(rnum==2)
       {
       Room r1 = new Room(grid1,31,57);
       Room r2 = new Room(grid2,24,84,in);
       Room r3 = new Room(grid3,28,91);
       }
       if(rnum==3)
       {
       Room r1 = new Room(grid1,31,57);
       Room r2 = new Room(grid2,24,84);
       Room r3 = new Room(grid3,28,91,in);
       }
        

        }
}
