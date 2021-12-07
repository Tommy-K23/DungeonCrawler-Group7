/**
 * This is the class responsible for running the <b>Main Menu</b>.
 */

import ansi_terminal.*;

public class Main {

	/**
	 * This creates a new <b>Main Menu</b> and runs it.
	 */
    public static void main(String args[]) {
        // put termain in raw mode
        Terminal.rawMode();

        // make and run the Game
        MainMenu mm = new MainMenu();
        mm.run();
        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

