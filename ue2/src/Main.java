import solve.*;
import model.*;
public class Main{
    public static void main(String[] args){
        //BruteForce brutus = new BruteForce();
        //brutus.solve();
        //solve.Backtracking back = new Backtracking();
        //back.solve();
        //

        Chessboard board = new Chessboard(8);
        board.set(true,2,3);
        board.set(true,2,5);

        Queen queen = new Queen(board,4,5);

        Queen other = new Queen(board,0,7);

        System.out.println(queen.compareTo(other));
    }
}
