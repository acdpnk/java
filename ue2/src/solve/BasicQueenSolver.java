package solve;
import model.Chessboard;
public abstract class BasicQueenSolver{
    /**
     * Abstract Class to derive Solver class implementations from. Provides a
     * method to check wether the current position is valid.
     */
    public abstract void solve();
        /**
         * Implemented by the derived Solvers.
         */
    public boolean checkChessboard(Chessboard board){
        /**
         * Checks wether a Chessboard has a valid position. All pieces are
         * considered Queens, there are no colors. To be valid, no Queen is
         * allowed to be threatened by another one.
         * @param board Chessboard to be checked.
         * @return True if position is valid, False otherwise.
         */
        int size = board.getSize();
        for(int i=0; i<size; i++){ //iterate over rows
            for(int j=0; j<size; j++){ //iterate over columns
                // we want to check for pieces threatening i,j
                // which only makes sense if there's a piece here
                if (! board.get(i,j)){
                    continue;
                }
                for(int l=1; i+l<size || j+l<size; l++){
                    if(i+l<size && board.get(i+l,j)){
                       // there's another piece on this column =>
                        return false;
                    }
                    if(j+l<size && board.get(i,j+l)){
                         // there's another piece on this row =>
                        return false;
                    }
                    if(j-l>=0 && i+l<size && board.get(i+l,j-l)){
                        // there's another piece on the lower left diagonale =>
                        return false;
                    }
                    if(i+l<size &&j+l<size && board.get(i+l,j+l)){
                        // there's another piece on the lower right diagonale =>
                        return false;
                    }
                }
            }
        }
        // all clear =>
        return true;
    }
}
