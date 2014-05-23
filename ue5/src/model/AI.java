package model;

public class AI extends Player
{
    public AI(int id)
    {
        super("Morgan", id, PlayerType.AI);
    }

    public int pickMove(Board board)
    {
        int choice = getPits()[(int) (Math.random()*6)];
        //System.out.println("picking " + choice);
        return choice;
    }
}
