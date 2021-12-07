/**
 * A <b>Position</b> represents a simple row/column space on the grid.
 */

public class Position {
    private int row;
    private int col;

	/**
	 * A default constructor for a <b>Position</b>.
	 */
    public Position() {
        row = 0;
        col = 0;
    }

	/**
	 * Constructor for a new <b>Position</b>.
	 * @param row the row number of the position.
	 * @param col the column number of the position.
	 */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
	/**
	 * Returns a whether a <b>Position</b> is equal to an <b>Object</b>s position.
	 * @param object an object occupying the space.
	 *
	 * @return true when the coordinates of the position and object match.
	 */
    public boolean equals(Object other) {
        Position op = (Position) other;

        return this.row == op.row && this.col == op.col;
    }

    /**
	 * Returns whether an <b>Object</b> is adjacent to a <b>Position</b>.
	 * @param other the position of the object.
	 *
	 * @return true if the difference between the positions is less than 2,
	 * false if it is greater than or equal to 2.
	 */
    public boolean isAdjacent(Position other) {
        int rowdiff = Math.abs(this.row - other.row);
        int coldiff = Math.abs(this.col - other.col);

        if (rowdiff + coldiff < 2) {
            return true;
        } else {
            return false;
        }
    }

	/**
	 * Gets the row of the <b>Position</b>.
	 *
	 * @return the row.
	 */
    public int getRow() {
        return row;
    }

	/**
	 * Gets the column of the <b>Position</b>.
	 * 
	 * @return the column.
	 */
    public int getCol() {
        return col;
    }
}

