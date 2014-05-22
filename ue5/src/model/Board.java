package model;

import java.util.Observable;

public class Board extends Observable
{
    private int[] pits;

    public Board()
    {
        pits = new int[14];
    }

    public int getSeeds(int pit)
    {
        return pits[pit];
    }

    public void setSeeds(int pit, int seeds)
    {
        pits[pit] = seeds;
        setChanged();
        notifyObservers(pit);
    }


}
