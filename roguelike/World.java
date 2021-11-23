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
"        ##           ##              ##    @   ##               ##             ##  ",
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
	    int roomNum = 1;
	    static Room r1;
            static Room r2;
	    static Room r3;
	    int rows;
	    int cols;
        public World() //Supposed to create the 3 levels, then set the current room to room 1.
        {
                r1 = new Room(grid1, 31, 56);

                r2 = new Room(grid2,24, 84 );

                r3 = new Room(grid3, 28, 91);

                currentRoom = r1;
        }

        public Room getCurrentRoom ()
        {return currentRoom;}


        public void changeRoom(int x)//takes an int as an arguement and translates it to change the currentRoom to its respective level.

        {

                if (x==1)
                {currentRoom=r1;}

                if(x==2)
                {currentRoom=r2;}

                if(x==3)
                {currentRoom=r3;}
		
		if (x>3)
		{
        //run endCredits method
		}

        }


        public static int getRoomNum()//returns the level number for the current room.
        {
                if (currentRoom==r1)
                {return 1;}

                if(currentRoom==r2)
                {return 2;}

                if (currentRoom==r3)
                {return 3;}
        return 4;
        }

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
