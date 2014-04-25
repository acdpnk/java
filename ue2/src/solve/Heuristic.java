package solve;
import model.*;
import java.util.ArrayList;
import java.util.Collections;
public class Heuristic extends BasicQueenSolver{
    private static final int dim = 50;
    private ArrayList<Queen> queens;
    private Chessboard board;

    public Heuristic(){
        this.queens = new ArrayList<Queen>();
        this.board = new Chessboard(dim);
    }
    public void solve(){
        for (int i=0;i<dim;i++){
            Queen queen = getLeastConflicted(i,i);
            board.set(true, queen.getX(), queen.getY());
            queens.add(queen);
            System.out.println(board);
        }

        int prev = -1;


        while (! checkChessboard(board)){

            Collections.sort(queens);

            System.out.println(queens);
            System.out.println(board);

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
        System.out.println(queens);
        System.out.println(board);
    }


    public Queen getLeastConflicted(int x, int y){
        System.out.println("ping");
        ArrayList<Queen> row = new ArrayList<Queen>();
        ArrayList<Queen> candidates = new ArrayList<Queen>();
        for (int i=0; i<dim; i++){
            row.add(new Queen(this.board,i,y));
        }

        Collections.sort(row);
        System.out.println(row);
        int best = row.get(0).getConflicts();

        while (row.isEmpty() == false && row.get(0).getConflicts()==best){
            candidates.add(row.get(0));
            row.remove(0);
        }

        Collections.shuffle(candidates);

        Queen replacement=candidates.get(0);
        candidates.remove(0);

        while (replacement.getX()==x){
            System.out.println(replacement);
            if(! candidates.isEmpty()){
                replacement = candidates.get(0);
                candidates.remove(0);
            } else {
                replacement = row.get(0);
                row.remove(0);
            }

        }
        System.out.println("putting " + replacement);

        return replacement;
    }
}
