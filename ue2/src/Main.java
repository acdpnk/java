import solve.*;
import model.*;
public class Main{
    public static void main(String[] args){
        //BruteForce brutus = new BruteForce();
        //brutus.solve();
        //solve.Backtracking back = new Backtracking();
        //back.solve();
        //

        //Chessboard board = new Chessboard(8);
        //board.set(true,2,3);
        //board.set(true,2,5);
        //board.set(true,5,6);

        //Queen queen = new Queen(board,2,3);

        //Queen other = new Queen(board,2,5);
        //Queen third = new Queen(board,5,6);


        //queen.determineConflicts(board);
        //other.determineConflicts(board);
        //third.determineConflicts(board);

        //System.out.println(queen);
        //System.out.println(other);
        //System.out.println(third);
        //System.out.println(board);

        Heuristic heureka = new Heuristic();
        heureka.solve();
    }
}
