package model;

public class AI extends Player
{
    private static final int INF = 2147483647;
    private static final int NEG_INF = -2147483648;
    private static final int START_DEPTH = 5;
    public AI(int id)
    {
        super("Morgan", id, PlayerType.AI);
    }

    public AI(String name, int id)
    {
        super(name, id, PlayerType.AI);
    }

    public int pickMove(Board board)
    {
        int choice = getPits()[(int) (Math.random()*6)];
        return choice;
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta)
    {
        //TODO
        return 0;
    }
}
