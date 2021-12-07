// Entity.java
// this class represents one moveable, drawable thing in the game

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

    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    // move the entity to a new location
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }
   
    public void setPosition(Position position){
	this.position = position;
    }
    // get the position of this entity
    public Position getPosition() {
        return position;
    }
    
    public void save(PrintWriter pw)//Save
    {
        pw.println("Entity");
        pw.println(getRow());
        pw.println(getCol());
        pw.println(display);
    }


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

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }

   
    // translate the entity in space, unless it would hit a wall
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

    // draw this entity to the screen
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

