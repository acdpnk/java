public class Test{
    public static void main(String[] args){
        Chessboard board = new Chessboard(8);
        board.set(true,4,4);
        board.set(true, 5,5);

        System.out.println(board);

        BasicQueenSolver solver = new BasicQueenSolver();

        if(solver.checkChessboard(board)){
            System.out.println("yup");
        } else {
            System.out.println("nope");
        }
    }
}
