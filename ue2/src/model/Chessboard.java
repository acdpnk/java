package model;
public class Chessboard {
    private int size;
    private boolean[][] fields;
    public Chessboard(int size){
        this.size = size;
        this.fields = new boolean[size][size];
    }

    public void set(boolean value, int x, int y){
        if(x>=size || y>=size){
            System.out.println("you set the piece outside the board (" + x + "," + y + ")");
            System.exit(1);
        }

        this.fields[x][y] = value;
    }

    public boolean get(int x, int y){
        if(x>=size || y>=size){
            System.out.println("you tried to take a piece from outside the board (" + x + "," + y + ")");
            System.exit(1);
        }

        return this.fields[x][y];
    }

    public Chessboard copy(){
        Chessboard board = new Chessboard(size);
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board.set(this.get(i,j), i, j);
            }
        }
        return board;
    }

    public String toString(){
        String str = new String();
         for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(fields[i][j]){
                    str += "[X]";
                } else {
                    str += "[ ]";
                }
            }
            str += "\n";
        }
        return str;
    }
    public int getSize(){return this.size;}
}
