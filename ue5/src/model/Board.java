package model;

import java.util.Observable;
import java.util.Arrays;

public class Board extends Observable implements Cloneable
{
    public static final int NUM_PITS = 14;
    private int[] pits;
    private Player playerOne, playerTwo, activePlayer;

    public Board(Player playerOne, Player playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        pits = new int[NUM_PITS];
    }

    // for cloning
    private Board(Player playerOne, Player playerTwo, Player activePlayer,
                    int[] pits)
    {
        this(playerOne, playerTwo);
        setActivePlayer(activePlayer);
        for (int i=0; i<NUM_PITS; i++)
        {
            this.pits[i] = pits[i];
        }
    }

    public Player getActivePlayer()
    {
        return activePlayer;
    }

    public void setActivePlayer(Player player)
    {
        activePlayer = player;
        setChanged();
        notifyObservers(activePlayer);
    }

    public Player getOpponent(Player player)
    {
        return playerOne == player ? playerTwo : playerOne;
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

    public boolean moveIsLegal(int pit)
    {
        // kalahas can't be chosen.
        if (pit == playerOne.getKalaha() || pit == playerTwo.getKalaha())
        {
            return false;
        }

        // if the pit doesn't belong to the active player, it can't be chosen.
        if (Arrays.binarySearch(activePlayer.getPits(), pit) < 0)
        {
            return false;
        }

        // if the pit is empty, it can't be chosen;
        if (getSeeds(pit) == 0)
        {
            return false;
        }

        return true;
    }
    public void move(int pit)
    {
        if (! moveIsLegal(pit))
        {
            return;
        }
        executeMove(pit);
    }
    public void executeMove(int pit)
    {
        int seeds = getSeeds(pit);
        setSeeds(pit,0);
        for (int i=1; i<=seeds; i++)
        {
            // if the pit we'd be putting seeds in is the opponent's kalaha,
            // skip it instead.
            if ((pit + i) % NUM_PITS == getOpponent(getActivePlayer()).getKalaha())
            {
                seeds++;
            }
            else
            {
                putSeed(pit + i);
            }
        }

        // if the last seed is dropped into an empty pit belonging to the
        // active player, capture all seeds in the opposing pit
        int lastSeed = pit + seeds;
        if (getSeeds(lastSeed) == 1 &&
            Arrays.binarySearch(activePlayer.getPits(),lastSeed % NUM_PITS)>=0
            && getSeeds(getOpposingPit(lastSeed)) > 0)
        {
            int capturedSeeds = getSeeds(getOpposingPit(lastSeed)) + 1;
            setSeeds(lastSeed, 0);
            setSeeds(getOpposingPit(lastSeed),0);
            setSeeds(activePlayer.getKalaha(),
                getSeeds(activePlayer.getKalaha()) + capturedSeeds);
        }

        // if the end is reached, no more playing
        // if the last seed dropped into the active player's kalaha,
        // they get another move. else, active status goes to the other
        // player.
        if (endReached())
        {
            clean();
        }
        else if (lastSeed % NUM_PITS != activePlayer.getKalaha())
        {
            setActivePlayer(getOpponent(getActivePlayer()));
        }
    }

    public boolean endReached()
    {
        int playerOneSeeds = 0;
        int playerTwoSeeds = 0;
        for (int pit : playerOne.getPits())
        {
            playerOneSeeds += getSeeds(pit);
        }
        for (int pit : playerTwo.getPits())
        {
            playerTwoSeeds += getSeeds(pit);
        }

        if (playerOneSeeds == 0 || playerTwoSeeds == 0)
        {
            return true;
        }
        return false;
    }

    private void clean()
    {
        int playerOneSeeds = getSeeds(playerOne.getKalaha());
        int playerTwoSeeds = getSeeds(playerTwo.getKalaha());
        for (int pit : playerOne.getPits())
        {
            playerOneSeeds += getSeeds(pit);
            setSeeds(pit, 0);
        }
        for (int pit : playerTwo.getPits())
        {
            playerTwoSeeds += getSeeds(pit);
            setSeeds(pit, 0);
        }

        setSeeds(playerOne.getKalaha(), playerOneSeeds);
        setSeeds(playerTwo.getKalaha(), playerTwoSeeds);
        setChanged();
        notifyObservers(playerOneSeeds > playerTwoSeeds ? playerOne : playerTwo);
    }

    @Override
    public Board clone()
    {
        return new Board(playerOne, playerTwo, activePlayer, pits);
    }

    @Override
    public String toString() // for debugging purposes
    {
        return  "Player One: " + playerOne.getName() + "\n" +
                "Player Two: " + playerTwo.getName() + "\n" +
                "active:     " + getActivePlayer().getName() + "\n" +
                "pits:       " + Arrays.toString(pits);
    }
}
