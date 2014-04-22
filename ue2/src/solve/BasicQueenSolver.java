package solve;

import model.Chessboard;

public abstract class BasicQueenSolver{
    public abstract void solve();
    public boolean checkChessboard(Chessboard board){
        int size = board.getSize();
        for(int i=0; i<size; i++){ //iterate over rows
            for(int j=0; j<size; j++){ //iterate over columns
                // we want to check for pieces threatening i,j
                // which only makes sense if there's a piece here
                if (! board.get(i,j)){
                    continue;
                }

                //for(int k=i+1; k<size; k++){
                    //if(board.get(i,k)){
                        //// there's another piece on this row =>
                        //System.out.println("row");
                        //return false;
                    //}
                //}

                for(int l=1; i+l<size || j+l<size; l++){
                    if(i+l<size && board.get(i+l,j)){
                       // there's another piece on this column =>
                        //System.out.println("col");
                        return false;
                    }
                    if(j+l<size && board.get(i,j+l)){
                         // there's another piece on this row =>
                        //System.out.println("row");
                        return false;
                    }
                    if(j-l>=0 && i+l<size && board.get(i+l,j-l)){
                        // there's another piece on the lower left diagonale =>
                        //System.out.println("loleft");
                        return false;
                    }
                    if(i+l<size &&j+l<size && board.get(i+l,j+l)){
                        // there's another piece on the lower right diagonale =>
                        //System.out.println("loright");
                        return false;
                    }
                }
            }
        }
        // all clear =>
        return true;
    }
}
