package model;
public class Chessboard {
    /**
     * Model for the Chessboard.
     */

    private int size;
    private boolean[][] fields;
    public Chessboard(int size){
        /**
         * initialize a Chessboard
         * @param size The Dimension of the Chessboard to be created.
         * @return A Chessboard object.
         */
        this.size = size;
        this.fields = new boolean[size][size];
    }

    public void set(boolean value, int x, int y){
        /**
         * Set or unset a piece on the designated field.
         * @param value New value for the designated field. True means
         *              occupied, False means empty.
         * @param x The row to set the piece in. Can be 0 to size-1.
         * @param y The column to set the piece in. Can be 0 to size-1.
         */
        if(x>=size || y>=size){
            System.out.println("you set the piece outside the board (" + x + "," + y + ")");
            System.exit(1);
        }

        this.fields[x][y] = value;
    }

    public boolean get(int x, int y){
        /**
         * Get the value of a field.
         * @param x the row to get the value from. Can be 0 to size-1.
         * @param y the column to get the value from. Can be 0 to size-1.
         * @return A boolean describing wether the field is occupied or empty.
         *          True means occupied, False means empty.
         */
        if(x>=size || y>=size){
            System.out.println("you tried to take a piece from outside the board (" + x + "," + y + ")");
            System.exit(1);
        }

        return this.fields[x][y];
    }

    public Chessboard copy(){
        /**
         * Method to copy the Chessboard.
         * @return A new Chessboard with the same occupation pattern.
         */
        Chessboard board = new Chessboard(size);
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board.set(this.get(i,j), i, j);
            }
        }
        return board;
    }

    public String toString(){
        /**
         * Stringify the Chessboard.
         * @return A String representation of the Chessboard. [ ] means "empty field", [X] means "occupied field".
         */
        String str = new String();
         for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(fields[j][i]){
                    str += "[X]";
                } else {
                    str += "[ ]";
                }
            }
            str += "\n";
        }
        return str;
    }
    public int getSize(){
        /**
         * Getter Method for the size of the Chessboard.
         * @return the size of the Chessboard in Integer form.
         */
        return this.size;
    }
}
