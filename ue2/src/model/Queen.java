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
