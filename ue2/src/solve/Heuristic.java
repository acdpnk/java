package solve;
import model.*;
import java.util.ArrayList;
import java.util.Collections;
public class Heuristic extends BasicQueenSolver{
    private static final int dim = 8;
    private ArrayList<Queen> queens;
    private Chessboard board;

    public Heuristic(){
        this.queens = new ArrayList<Queen>();
        this.board = new Chessboard(dim);
    }
    public void solve(){
        for (int i=0;i<dim;i++){
            Queen queen = getLeastConflicted(i);
            board.set(true, queen.getX(), queen.getY());
            queens.add(queen);
            System.out.println(board);
        }

        int prev = -1;


        while (! checkChessboard(board)){

            Collections.sort(queens);
            Queen mostConflicted = queens.get(dim-1);
            if(mostConflicted.getY()==prev){
               Collections.shuffle(queens);
               mostConflicted = queens.get(dim-1);
            }

            Queen replacement = getLeastConflicted(mostConflicted.getY());

            board.set(false, mostConflicted.getX(), mostConflicted.getY());
            board.set(true, replacement.getX(), replacement.getY());

            queens.remove(dim-1);
            queens.add(replacement);

            for (Queen q : queens){
                q.determineConflicts(board);
            }

            prev = replacement.getY();
            System.out.println(queens);
            System.out.println(board);
        }
        System.out.println(queens);
        System.out.println(board);
    }


    public Queen getLeastConflicted(int y){
        ArrayList<Queen> row = new ArrayList<Queen>();

        for (int i=0; i<dim; i++){
            row.add(new Queen(this.board,i,y));
        }

        Collections.sort(row);
        return row.get(0);
    }
}
