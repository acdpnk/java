public class BruteForce extends BasicQueenSolver{
    public static final int size = 8;
    public void solve(){
        int solutions = 0;
        for(int a=0;a<size;a++){
          for(int b=0;b<size;b++){
            for(int c=0;c<size;c++){
              for(int d=0;d<size;d++){
                for(int e=0;e<size;e++){
                  for(int f=0;f<size;f++){
                    for(int g=0;g<size;g++){
                      for(int h=0;h<size;h++){
                        Chessboard board = new Chessboard(size);
                        board.set(true,0,a);
                        board.set(true,1,b);
                        board.set(true,2,c);
                        board.set(true,3,d);
                        board.set(true,4,e);
                        board.set(true,5,f);
                        board.set(true,6,g);
                        board.set(true,7,h);
                        if(checkChessboard(board)){
                            System.out.println(board + "\n");
                            solutions++;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        System.out.println(solutions + " solutions found.");
    }
}
