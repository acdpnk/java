package model;

import java.util.Observable;

public class Board extends Observable
{
    public static final int NUM_PITS = 14;
    private int[] pits;
    private Player playerOne, playerTwo;

    public Board(Player playerOne, Player playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        pits = new int[NUM_PITS];
    }

    public int getSeeds(int pit)
    {
        return pits[pit % NUM_PITS];
    }

    public void setSeeds(int pit, int seeds)
    {
        pits[pit % NUM_PITS] = seeds;
        setChanged();
        notifyObservers(pit % NUM_PITS);
    }

    public void putSeed(int pit)
    {
        pits[pit % NUM_PITS] = pits[pit % NUM_PITS] + 1;
        setChanged();
        notifyObservers(pit % NUM_PITS);
    }

    public int getOpposingPit(int pit)
    {
        return 12 - (pit % NUM_PITS);
    }

    public void setUp()
    {
        for (int pit : playerOne.getPits())
        {
            setSeeds(pit,3);
        }

        for (int pit : playerTwo.getPits())
        {
            setSeeds(pit,3);
        }

        setSeeds(playerOne.getKalaha(),0);
        setSeeds(playerTwo.getKalaha(),0);
    }

}
