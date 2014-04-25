package solve;
import model.*;
import java.util.ArrayList;
import java.util.Collections;
public class Heuristic extends BasicQueenSolver{
    /**
     * Solver class implementing a heuristic solving algorithm for the Many
     * Queens Problem.
     */
    private static final int STD_DIM = 50;
    private int dim;
    private ArrayList<Queen> queens;
    private Chessboard board;

    public Heuristic(int dim){
        /**
         * Constructor.
         * @param dim Dimension of the Board to be solved
         * @return Solver
         */
        this.dim = dim;
        this.queens = new ArrayList<Queen>();
        this.board = new Chessboard(dim);
    }
    public Heuristic(){
        /**
         * Constructor using standard dimension 50
         * @return Solver
         */
        this(STD_DIM);
    }
    public void solve(){
        /**
         * solve a board of the specified dimension and print the result
         */
        for (int i=0;i<dim;i++){
            Queen queen = getLeastConflicted(i,i);
            board.set(true, queen.getX(), queen.getY());
            queens.add(queen);
        }

        int prev = -1;


        while (! checkChessboard(board)){

            Collections.sort(queens);

            Queen mostConflicted = queens.get(dim-1);
            if(mostConflicted.getY()==prev){
               Collections.shuffle(queens);
               mostConflicted = queens.get(dim-1);
            }

            Queen replacement = getLeastConflicted(mostConflicted.getX(), mostConflicted.getY());

            board.set(false, mostConflicted.getX(), mostConflicted.getY());
            board.set(true, replacement.getX(), replacement.getY());

            queens.remove(dim-1);
            queens.add(replacement);

            for (Queen q : queens){
                q.update(board);
            }

            prev = replacement.getY();

            //try{
                //System.in.read();
            //}
            //catch(Exception e){

            //}
        }
        //System.out.println(queens);
        System.out.println(board);
    }


    private Queen getLeastConflicted(int x, int y){
        /**
         * get the queen with the least possible conflict in row y, that is not
         * also in column x. when multiple minima exists one is chosen at random.
         * @param x column (returned Queen will *not* be here)
         * @param y row (returned Queen *will* be here)
         * @return Queen
         */
        ArrayList<Queen> row = new ArrayList<Queen>();
        ArrayList<Queen> candidates = new ArrayList<Queen>();
        for (int i=0; i<dim; i++){
            row.add(new Queen(this.board,i,y));
        }

        Collections.sort(row);
        int best = row.get(0).getConflicts();

        while (row.isEmpty() == false && row.get(0).getConflicts()==best){
            candidates.add(row.get(0));
            row.remove(0);
        }

        Collections.shuffle(candidates);

        Queen replacement=candidates.get(0);
        candidates.remove(0);

        while (replacement.getX()==x){
            if(! candidates.isEmpty()){
                replacement = candidates.get(0);
                candidates.remove(0);
            } else {
                replacement = row.get(0);
                row.remove(0);
            }

        }
        //System.out.println("putting " + replacement);

        return replacement;
    }
}
