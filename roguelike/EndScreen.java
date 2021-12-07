import ansi_terminal.*;

/**
 * This class is an "<b>End Screen</b>" that appears when the <b>Player</b>
 * has successfully escaped the lab. 
 *
 * @author TK
 */
public class EndScreen {

private String end[] = {
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
                  "                       //   Press Enter to go back to the Main Menu                    //                  ",//17              
                  "                       //                                                  Created 2021//                  ",//18
                  "                       //////////////////////////////////////////////////////////////////                  ",//19
                  "                                                                                                           ",//20
                  "                                                                                                           ",//21
 
};

private int rows = 21;
private int cols = 107;
private boolean quit = false;
MainMenu menu = new MainMenu();
private String status = "";

	/**
	 * Constructor for the <b>End Screen</b>.
	 */
	public EndScreen() {}

	/**
	 * Runs the end screen (draws to screen and waits for
	 * key press to quit).
	 */
	public void run() {
		Terminal.clear();
		drawMap();
		while (!quit){
			Key key = Terminal.getKey();
			handleKey(key);
		}
		Terminal.clear();
	}

	/**
	 * Handles the key press to exit back to the <b>Main Menu</b>.
	 * @param key the key press.
	 */
	public void handleKey(Key key){
		switch(key){
			case ENTER:
				printStatus("Returning to Menu...");
				Terminal.pause(1.5);
				quit = true;
				break;
		}
	}

	/**
	 * Prints a status to the screen.
	 * @param status a message.
	 */
	public void printStatus(String status){
		Terminal.warpCursor(0,0);
		System.out.print(status);
	}

	/**
	 * Draws the <b>End Screen</b> to the screen.
	 */
	public void drawMap(){
		Terminal.reset();
		Terminal.clear();
		char cell = ' ';
		for (int r = 0; r<22; r++){
			for (int c=0; c<107; c++) {
				cell = end[r].charAt(c);
				System.out.print(cell);
			}	
		System.out.print("\r\n");
		}	
	}
}
