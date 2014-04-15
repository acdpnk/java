public class Test{
    public static void main(String[] args){
        Chessboard board = new Chessboard(8);
        board.set(true,9,7);
        board.set(true, 4,3);

        System.out.println(board);
    }
}
