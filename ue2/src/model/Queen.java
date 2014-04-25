package model;
public class Queen implements Comparable<Queen>{
    private int x, y, conflicts;
    public Queen(Chessboard board, int x, int y){
        this.x = x;
        this.y = y;
        this.conflicts = determineConflicts(board);
    }


    public int getConflicts(){
        return this.conflicts;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public String toString(){
        return "{X: " + this.getX() + ", Y: " + this.getY() + ", Conflicts: " + this.getConflicts() + "}";
    }

    @Override
    public int compareTo(Queen other){
        return this.getConflicts()-other.getConflicts();
    }

    public void update(Chessboard board){
        this.conflicts = this.determineConflicts(board);
    }

    public int determineConflicts(Chessboard board){
        int size = board.getSize();
        int threats = 0;

        for(int i=0; i<size; i++){
            if(this.x==i){
                continue;
            }
            if(board.get(i,this.y)){
                threats++;
            }
        }
        for(int j=0; j<size; j++){
            if(this.y==j){
                continue;
            }
            if(board.get(this.x,j)){
                threats++;
            }
        }

        for(int k=1; this.x-k>=0 && this.y-k>=0; k++){
            if(board.get(this.x-k, this.y-k)){
                threats++;
            }
        }

        for(int l=1; this.x+l<size && this.y-l>=0; l++){
            if(board.get(this.x+l, this.y-l)){
                threats++;
            }
        }

        for(int m=1; this.x+m<size && this.y+m<size; m++){
            if(board.get(this.x+m, this.y+m)){
                threats++;
            }
        }

        for(int n=1; this.x-n>=0 && this.y+n<size; n++){
            if(board.get(this.x-n, this.y+n)){
                threats++;
            }
        }
        return threats;
    }

}
    //public int determineConflicts(Chessboard board){
        //int size = board.getSize();
        //int conflicts = 0;

        //for (int i=0; i<size; i++){
            //if(i==y){
                //continue;
            //}
            //if(board.get(x,i)){
                //conflicts++;
            //}
        //}
        //for (int j=0; j<size; j++){
            //if(j==y){
                //continue;
            //}
            //if(board.get(j,y)){
                //conflicts++;
            //}
        //}

        //int xOffset = x-y;

        //for (int k=0; k<size; k++){
            //if(k+xOffset<0){
                //// field outside board
                //continue;
            //}
            //if(k+xOffset==x){
                //// field is the field we're checking conflict with
                //continue;
            //}
            //if(! ((k+xOffset)<size)){
                //// field (and all following fields) outside board
                //break;
            //}
            //if(board.get(k+xOffset, k)){
                //// conflict
                //conflicts++;
            //}
        //}

        //xOffset = (size-1) + xOffset;
        //for (int l=0; l<size; l++){
            //if(! ((xOffset-l)<size)){
                //// field outside board
                //continue;
            //}
            //if(xOffset-l==x){
                //// field is the field we're checking for conflict with
                //continue;
            //}
            //if(xOffset-l<0){
                //// field (and all following fields) outside board
                //break;
            //}
            //if(board.get(xOffset-l,l)){
                //// conflict
                //conflicts++;
            //}
        //}
        //return conflicts;
    //}




    //     int k=x-y;
    //     int l=0;
    //     while (k<size && l<size){
    //         if(k<0 || l<0 || k==x || l==y){
    //             k++;
    //             l++;
    //             continue;
    //         }
    //         if(board.get(k,l)){
    //             conflicts++;
    //             k++;
    //             l++;
    //         }
    //     }

    //     int m=x+y;
    //     int n=0;
    //     while (m>0 && n<size){
    //         if()
    //     }
    // }

    // private int getConflicts(Chessboard board){
    //     int size = board.getSize();
    //     int conflicts;
    //     for(int i=0; i<size; i++){ //iterate over rows
    //         for(int j=0; j<size; j++){ //iterate over columns
    //             // we want to check for pieces threatening i,j
    //             // which only makes sense if there's a piece here
    //             if (! board.get(i,j)){
    //                 continue;
    //             }
    //             for(int l=1; i+l<size || j+l<size; l++){
    //                 if(i+l<size && board.get(i+l,j)){
    //                    // there's another piece on this column =>
    //                    conflicts++;
    //                 }
    //                 if(j+l<size && board.get(i,j+l)){
    //                      // there's another piece on this row =>
    //                     conflicts++;
    //                 }
    //                 if(j-l>=0 && i+l<size && board.get(i+l,j-l)){
    //                     // there's another piece on the lower left diagonale =>
    //                     conflicts++;
    //                 }
    //                 if(i+l<size &&j+l<size && board.get(i+l,j+l)){
    //                     // there's another piece on the lower right diagonale =>
    //                     conflicts++;
    //                 }
    //             }
    //         }
    //     }
    //     // all clear =>
    //     return true;
    // }
