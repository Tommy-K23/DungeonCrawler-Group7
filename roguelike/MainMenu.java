
import java.util.ArrayList;
import ansi_terminal.*;


public class MainMenu
{
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


        public MainMenu()
        {}

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

        private void select (int option)
        {
                switch (option)
                {

                        case 1:
                                /*Terminal.warpCursor(21, 0);
                                String name = Terminal.getLine("What is your name? ");
                                Terminal.warpCursor(21, 0);
                                String profession = Terminal.getLine("Hello, " + name + "! What is your profession? ");
                                Terminal.warpCursor(21, 0);*/
                                Game game = new Game();
                                game.run();
                                drawMap();
                                break;

                        case 2:
                                printStatus("Under construction!");
                                break;

                        case 3:
                                printStatus("Under construction!");
                                break;

                        case 4:
                                printStatus("Quitting...");
                                Terminal.pause(2);
                                quit=true;
                                break;

                }
        }

        private void printStatus(String status)
        {
                Terminal.warpCursor(0,0);
                System.out.print(status);
        }



        private void cursorUp()
        {
                if (option>1)
                { option--;}
        }

        private void cursorDown()
        {
                if (option < 4)
                {option++;}
        }

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