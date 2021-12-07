/**
 * This class represents a base for a drawable <b>Entity</b>/"thing" in the game.
 * No entity is allowed to move through walls. Entities are displayed with colored characters.
 *
 */
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class Entity {
    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;
	
	/**
	 * Constructor for an <b>Entity</b>.
	 * @param row the row position on the grid.
	 * @param col the column position on the grid.
	 * @param display display character for the entity on the grid.
	 * @param color color of the display character.
	 */
    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    /**
	 * This method moves the <b>Entity</b> to a new location on the grid.
	 * @param row the row position on the grid.
	 * @param col the column position on the grid.
	 */
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }
   
	/**
	 * This method moves the <b>Entity</b> to a new position.
	 * @param position the new position for the entity.
	 */
    public void setPosition(Position position){
	this.position = position;
    }
   
	/**
	 * Gets the entity's position.
	 *
	 * @return the entity's position.
	 */
    public Position getPosition() {
        return position;
    }
   
   	/**
	 * Adds the <b>Entity</b> to the save game file.
	 * @param pw a <b>PrintWriter</b> that is used to write the data
	 * into the save file.
	 */
    public void save(PrintWriter pw)//Save
    {
        pw.println("Entity");
        pw.println(getRow());
        pw.println(getCol());
        pw.println(display);
    }

	/**
	 * Loads the <b>Entity</b> from the save file.
	 * @param in a Scanner that reads the data from the save file.
	 */
    public Entity (Scanner in , Color color)//Load
    {
        this.color = color;
        in.nextLine();
	    in.nextLine();
        int row= in.nextInt();
        int col= in.nextInt();
	    in.nextLine();
        String d = in.nextLine();
	//String clr = in.nextLine();
        Position position = new Position(row, col);
	setPosition(position);
        display = d.charAt(0);

    }
	
	/**
	 * Gets the entity's row position.
	 *
	 * @return the entity's row position.
	 */
    public int getRow() {
        return position.getRow();
    }

	/**
	 * Gets the entity's column position.
	 *
	 * @return the entity's column position.
	 */
    public int getCol() {
        return position.getCol();
    }

   
    /**
	 * Allows the <b>Entity</b> to move in the game space.
	 * Will not allow it to move through walls.
	 * @param rowChange changes the row the entity is currently in.
	 * @param colChange changes the column the entity is currently in.
	 * @param room the room that the entity is in.
	 *
	 * @return true if the entity can occupy the space, return false
	 * if the entity cannot.
	 */
    public boolean move(int rowChange, int colChange, Room room) {
        // find new position
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (room.canGo(newRow, newCol)) {
            // draw a space where it currently is
            Terminal.warpCursor(position.getRow(), position.getCol());
            System.out.print(" ");

            // and then move it
            position = new Position(newRow, newCol);
            return true;
        } else {
            return false;
        }
    }

    /**
	 * Draws the <b>Entity</b> to the screen.
	 */
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

