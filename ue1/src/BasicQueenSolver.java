public  class BasicQueenSolver{
    //public abstract void solve();
    public boolean checkChessboard(Chessboard board){
        int size = board.getSize();
        int[] rows = new int[size];
        for(int i=0; i<size; i++){
            int pos = -1; //can't use null, -1 is not a valid value so we use that.
            for(int j=0; j<size; j++){
                if(board.get(i,j) && pos!=-1){
                    return false;
                }
                else if (board.get(i,j)){pos=j;}
            }
            rows[i]=pos;
            System.out.println(rows);
        }

        for (int k=0; k<size; k++){
            for (int l=0; l<size; l++){
                if (rows[k]<0 && rows[l]<0 || k==l){
                    continue;
                }
                else if (rows[k]==rows[l]){
                    System.out.println("meep");
                    return false;
                }
                else if (k+l<size && rows[l]==k+l){
                    return false;
                }
                else if (k-l>=0 && rows[l]==k-l){
                    return false;
                }
            }
        }

        return true;
    }

}
