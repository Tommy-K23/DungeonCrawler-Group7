
import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class MainMenu
{
 /**
*The MainMenu Class is what gets called from Main.
*This class contains the Game class, and all subsequent classes.
*Allows the player to choose different options from a nifty little menu.
*/       
        int option;

        String [] menuGraphic =
        {
                "                                                                                                           ",//0
                "                                                                                                           ",//1
                "                       //////////////////////////////////////////////////////////////////                  ",//2
                "                       //                                                              //                  ",//3
                "                       //                   Welcome to Broken Glass.                   //                  ",//4
                "                       //                                                              //                  ",//5
                "                       //                                                              //                  ",//6
                "                       //                       __ New Game                            //                  ",//7
                "                       //                       __ Load Game                           //                  ",//8
                "                       //                       __ About                               //                  ",//9
                "                       //                       __ Quit                                //                  ",//10
                "                       //                                                              //                  ",//11
                "                       //                                                              //                  ",//12
                "                       //                                                              //                  ",//13
                "                       //                                                              //                  ",//14
                "                       //                                                              //                  ",//15
                "                       //                                                              //                  ",//16
                "                       //      Use Arrow keys to move cursor, and Enter to select.     //                  ",//17
                "                       //                                                  Created 2021//                  ",//18
                "                       /////////////////////////////////////////////////////////////////                   ",//19
                "                                                                                                           ",//20
                "                                                                                                           ",//21
        };
        int row=22;
        int col=106;
        int r=0;
        int c=0;
        int cursorRow=7;
        int cursorCol=48;
        String status="";
        boolean quit = false;
        Game game = null;
/**
*Simply creates a MainMenu object. Does nothing else.
*/
        public MainMenu()
        {}
/**
*Opens up the Main Menu and allows the player to interact with it, and subsequently, an instance of the Game.
*/
        public void run()
        {
                Terminal.clear();
                drawMap();
                while(!quit)
                {
                        Key key = Terminal.getKey();
                        handleKey(key);
                        drawCursor(option);
                }
                Terminal.clear();
        }
/**
*Moves the selection cursor to its correct position based off of what option number it is supposed to be moved to.
*@param option is the number that correlates to the option you want the cursor to be moved to.
*/
        private void drawCursor(int option)
        {
            
                if (option==1)
                {
                        Terminal.warpCursor(cursorRow,cursorCol);
                        System.out.print("__");
                        Terminal.warpCursor(7,48);
                        System.out.print(">>");
                        Terminal.warpCursor(0,0);
                        cursorRow=7;
                        cursorCol=48;
                }

                if(option==2)
                {
                        Terminal.warpCursor(cursorRow,cursorCol);
                        System.out.print("__");
                        Terminal.warpCursor(8,48);
                        System.out.print(">>");
                        Terminal.warpCursor(0,0);
                        cursorRow=8;
                        cursorCol=48;

                }

                if(option==3)
                {
                        Terminal.warpCursor(cursorRow,cursorCol);
                        System.out.print("__");
                        Terminal.warpCursor(9,48);
                        System.out.print(">>");
                        Terminal.warpCursor(0,0);
                        cursorRow=9;
                        cursorCol=48;
                }

                if(option==4)
                {
                        Terminal.warpCursor(cursorRow,cursorCol);
                        System.out.print("__");
                        Terminal.warpCursor(10,48);
                        System.out.print(">>");
                        Terminal.warpCursor(0,0);
                        cursorRow=10;
                        cursorCol=48;
                }
        }
        /**
        *Allows the user to provide different inputs, and makes the system act accordingly.
        *@param key is the raw input that the user passes in from their keyboard.
        */
        private void handleKey(Key key)
        {
                switch (key)
                {
                        case UP: cursorUp();
                                 break;

                        case DOWN: cursorDown();
                                   break;

                        case ENTER: select(option);
                                    break;
                }

        }
        /**
        *Used when the player presses the enter key while on a specific option.
        *@param option is the number correlating to the option that the player is executing.
        */
        private void select (int option)
        {
                switch (option)
                {

                        case 1:
                                game = new Game();
                                game.run();
                                Terminal.clear();
                                drawMap();
                                break;

                        case 2:
                                
                                try 
                                {
                                File f = new File("save.txt");
                                Scanner in = new Scanner(f);
                                game = new Game(in);
                                in.close();
                                game.run();
                                Terminal.clear();
                                drawMap();
                                }
                                catch (Exception e)
                                {
                                Terminal.cookedMode();
                                e.printStackTrace();
                                System.exit(0);
                                //System.out.print(e);
				//printStatus("File not Found!");
                                }
                                break;

                        case 3:
                                printStatus("This game was made by Ethan, Ashley, and Thomas. It is about a scientist trying to escape from his lab after a series of accidents in the facility.");
                                break;

                        case 4:
                                printStatus("Quitting...");
                                Terminal.pause(2);
                                quit=true;
                                break;

                }
        }
/**
*Provides a convenient way to print messages to the screen in a uniform spot.
*/
        private void printStatus(String status)
        {
                Terminal.warpCursor(0,0);
                System.out.print(status);
        }


/**
*Method that decrements the Option number, so long as it stays within predetermined bounds.
*The option integer is not allowed to be less than one.
*/
        private void cursorUp()
        {
                if (option>1)
                { option--;}
        }
/**
*Method that increments the option number, so long as it stays within predetermined bounds.
*The option integer is not allowed to increment past 4.
*/
        private void cursorDown()
        {
                if (option < 4)
                {option++;}
        }
/**
*Method that prints the contents of the graphical display.
*/
        private void drawMap()
        {
                Terminal.reset();
                Terminal.clear();
                char cell=' ';
                for (int r=0; r<22;r++)
                {
                        for (int c=0; c<106;c++)
                        {
                                cell = menuGraphic[r].charAt(c);
                                System.out.print(cell);
                        }
                        System.out.print("\n\r");
                }
        }
}
