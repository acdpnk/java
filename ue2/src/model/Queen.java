package model;
public class Queen implements Comparable<Queen>{
    /**
     * Queen Class
     */
    private int x, y, conflicts;
    public Queen(Chessboard board, int x, int y){
        /**
         * Constructor
         * @param board Chessboard this queen is sitting on
         * @param x Column
         * @param y Row
         * @return Queen Object
         */
        this.x = x;
        this.y = y;
        this.conflicts = determineConflicts(board);
    }

    public int getConflicts(){
        /**
         * Getter for Conflicts
         * @return number of conflicts to othe queens on the board. determined
         *          by the queens that are set *on the board* - no relationship
         *          to other Queen objects exists.
         */
        return this.conflicts;
    }
    public int getX(){
        /**
         * Getter for the Queen's X Coordinate (it's column)
         * @return X
         */
        return this.x;
    }
    public int getY(){
        /**
         * Getter for the Queen's Y Coordinate (it's Row)
         * @return Y
         */
        return this.y;
    }

    @Override
    public String toString(){
        /**
         * toString implementation.
         * @return X, Y and number of Conflicts comma separated in curly braces, in String format
         */
        return "{X: " + this.getX() + ", Y: " + this.getY() + ", Conflicts: " + this.getConflicts() + "}";
    }

    @Override
    public int compareTo(Queen other){
        /**
         * compareTo implementation. more Conflicts is rated higher (Queens will be sorted from lowest to highest amount of Conflicts)
         * @return difference in Conflicts as int
         */
        return this.getConflicts()-other.getConflicts();
    }

    public void update(Chessboard board){
        /**
         * update the number of conflicts relating to a Chessboard (again,
         * no connection between the objects as per spec - this will not work
         * if you pass in the wrong board)
         * @param board the Chessboard (see above)
         */
        this.conflicts = this.determineConflicts(board);
    }

    private int determineConflicts(Chessboard board){
        /**
         * get the number of Conflicts relating to a Chessboard
         * @param board the Chessboard
         * @return the number of Conflicts as int
         * @see update()
         */
        int size = board.getSize();
        int threats = 0;

        for(int i=0; i<size; i++){
            if(this.x==i){
                continue;
            }
            if(board.get(i,this.y)){
                threats++;
            }
        }
        for(int j=0; j<size; j++){
            if(this.y==j){
                continue;
            }
            if(board.get(this.x,j)){
                threats++;
            }
        }
        for(int k=1; this.x-k>=0 && this.y-k>=0; k++){
            if(board.get(this.x-k, this.y-k)){
                threats++;
            }
        }
        for(int l=1; this.x+l<size && this.y-l>=0; l++){
            if(board.get(this.x+l, this.y-l)){
                threats++;
            }
        }
        for(int m=1; this.x+m<size && this.y+m<size; m++){
            if(board.get(this.x+m, this.y+m)){
                threats++;
            }
        }
        for(int n=1; this.x-n>=0 && this.y+n<size; n++){
            if(board.get(this.x-n, this.y+n)){
                threats++;
            }
        }
        return threats;
    }
}
