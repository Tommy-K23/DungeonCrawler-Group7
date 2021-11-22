import java.util.Scanner;
import java.util.PrintWriter;
public class World
{
        String grid1 [] = 
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


        String grid2 [] = 
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
        
        String grid3 [] =
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

        Room currentRoom = null;
	int roomNum = 1;
	Room r1;
	Room r2;
	Room r3;
        public World() //Supposed to create the 3 levels, then set the current room to room 1.
        {
                r1 = new Room(grid1);

                r2 = new Room(grid2);

                r3 = new Room(grid3);

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
		{//run endCredits method
		}
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

	//compiler error with r1, r2, r3 being equalled as Scanners
        public World (Scanner in)
        {
       // r1 = new Room(in);
       // r2 = new Room(in);
       // r3 = new Room(in);
        }
}
