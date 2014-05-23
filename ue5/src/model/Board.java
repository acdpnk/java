package model;

import java.util.Observable;

public class Board extends Observable
{
    public static final int NUM_PITS = 14;
    private int[] pits;

    public Board()
    {
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

}
