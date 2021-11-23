import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class World
{
        String[] grid1 = 
        {
"                       ############                     ",
"                       ##      ^ ##                     ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"   ######################        ###################### ",
"   ##  @               ##        ##                  ## ",
"   ##                  ##        ##                  ## ",
"   ##                  ##        ##                  ## ",
"   ##                            %                   ## ",
"   ######################        ###################### ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"   ######################        ###################### ",
"   ##                                                ## ",
"   ##                  ##      # ##                  ## ",
"   ##        %         #              #              ## ",
"   ##^                 ## #      ##                  ## ",
"   ######################        ###################### ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"                       ##   %    ##                     ",
"                       ##        ##                     ",
"                       ##        ##                     ",
"                       #####  #####                     ",
"                       ## *      ##                     ",
"                       ##        ##                     ",
"                       ############                     ",

        };


        String[] grid2 = 
        {
"       #############################                                               ",
"       ##          |___|          ##                                               ",
"       ##            *            ##                                               ",
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

	String grid4[] = {
                  "                                                                                                           ",//0
                  "                                                                                                           ",//1
                  "                       //////////////////////////////////////////////////////////////////                  ",//2
                  "                       //                                                              //                  ",//3
                  "                       //                 Congratulations! You Escaped!                //                  ",//4
                  "                       //                                                              //                  ",//5
                  "                       //                                                              //                  ",//9
                  "                       //                                                              //                  ",//10
                  "                       //                        Developers:                           //                  ",//6
		  "                       //                         -Ethan T.                            //                  ",//7
		  "                       //                         -Ashley Q .                          //                  ",//8
		  "                       //                         -Tommy K.                            //                  ",//11
                  "                       //                                                              //                  ",//12
                  "                       //                                                              //                  ",//13
                  "                       //                    Thank you for playing!                    //                  ",//14
                  "                       //                                                              //                  ",//15
                  "                       //                                                              //                  ",//16
                  "                       //                                                              //                  ",//17
                  "                       //                                                  Created 2021//                  ",//18
                  "                       //////////////////////////////////////////////////////////////////                  ",//19
                  "                                                                                                           ",//20
                  "                                                                                                           ",//21
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
	
		r4 = new Room(grid4, 21, 106);

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
		{Terminal.clear();
		 currentRoom=r4;
        	 roomNum = 4;

		}

        }


        public static int getRoomNum()//returns the level number for the current room.
        {
               // if (currentRoom==r1)
               // {return 1;}

               // if(currentRoom==r2)
               // {return 2;}

               // if (currentRoom==r3)
               // {return 3;}
        return roomNum;
        }

	public int getRows()
	{return rows;}

	public int getCols()
	{return cols;}


        public void save(PrintWriter pw)
        {
        r1.save(pw);
        r2.save(pw);
        r3.save(pw);
        }

        public World (Scanner in)
        {
       Room r1 = new Room(grid1, 31, 57);
       Room r2 = new Room(grid2, 24, 84);
       Room r3 = new Room(grid3, 28, 91);
        }
}
