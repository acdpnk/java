package solve;
import model.Chessboard;
public class Backtracking extends BasicQueenSolver{
    /**
     * Backtracking Implementation.
     */
    public static final int size = 8; // dimension of board to be solved.
    public static int solutions;

    public void solve(){
        /**
         * Method to solve the Many Queens Problem with backtracking
         * Prints the solutions it finds as well as the number of solutions it
         * finds.
         */
        solutions = 0;
        for(int i=0;i<size;i++){
            Chessboard board = new Chessboard(size);
            board.set(true, 0,i);
            backtracking(1,size,board);
        }
        System.out.println(solutions + " solutions found.");
    }
    private void backtracking(int n, int dim, Chessboard board){
        //if(n>dim){return;}
        if(n==dim){
            System.out.println(board);
            solutions++;
            return;
        }

        for(int i=0;i<dim;i++){
            Chessboard copy = board.copy();
            copy.set(true,n,i);
            if(checkChessboard(copy)){
                backtracking(n+1,dim,copy);
            }
        }
    }
}
