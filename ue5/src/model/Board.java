package model;

import java.util.Observable;
import java.util.Arrays;

public class Board extends Observable
{
    public static final int NUM_PITS = 14;
    private int[] pits;
    private Player playerOne, playerTwo, activePlayer;

    public Board(Player playerOne, Player playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        setActivePlayer(playerOne);
        pits = new int[NUM_PITS];
    }

    public Player getActivePlayer()
    {
        return activePlayer;
    }

    public void setActivePlayer(Player player)
    {
        this.activePlayer = player;
    }

    public Player getOpponent()
    {
        return playerOne == activePlayer ? playerTwo : playerOne;
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

    public void move(int pit)
    {
        // kalahas can't be chosen.
        if (pit == playerOne.getKalaha() || pit == playerTwo.getKalaha())
        {
            return;
        }

        // if the pit doesn't belong to the active player, it can't be chosen.
        if (Arrays.binarySearch(activePlayer.getPits(), pit) < 0)
        {
            return;
        }

        // if the pit is empty, it can't be chosen;
        if (getSeeds(pit) == 0)
        {
            return;
        }

        System.out.println(activePlayer.getName() + ": " + pit);

        int seeds = getSeeds(pit);
        setSeeds(pit,0);
        for (int i=1; i<=seeds; i++)
        {
            // if the pit we'd be putting seeds in is the opponent's kalaha,
            // skip it instead.
            if ((pit + i) % NUM_PITS == getOpponent().getKalaha())
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

        if (endReached())
        {
            System.out.println("END");
            endGame();
        }
        // if the last seed dropped into the active player's kalaha,
        // they get another move. else, active status goes to the other
        // player.
        if (lastSeed % NUM_PITS != activePlayer.getKalaha())
        {
            activePlayer = getOpponent();
            System.out.println(activePlayer.getName() + "\'s turn");
        }

        while (activePlayer.getType() == PlayerType.AI)
        {
            move(((AI) activePlayer).pickMove(this));
        }
    }

    private boolean endReached()
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

    private void endGame()
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
    }
}
