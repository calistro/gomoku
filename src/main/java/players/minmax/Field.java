package players.minmax;

public class Field {
    protected final int row;
    protected final int col;
    
    protected int index;

    /**
     * Default constructor for a field, set to out of bounds.
     */
    protected Field() {
        this.row = 0;
        this.col = 0;
        this.index = 3;
    }

    /**
     * Create a field with a row/column identifier.
     * @param row Row on the board
     * @param col Column on the board
     */
    protected Field(int row, int col) {
        this.row = row;
        this.col = col;
        this.index = 0;
    }
}