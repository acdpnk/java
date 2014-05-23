package control;

import javax.swing.JFrame;
import java.util.Arrays;
import view.*;
import model.*;

public class KalahaController
{
    private JFrame testframe;
    private PitPane pitpane;
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;

    //private final int[] PLAYER_ONE_PITS = {0,1,2,3,4,5};
    //private final int[] PLAYER_TWO_PITS = {7,8,9,10,11,12};
    //private final int PLAYER_ONE_KALAHA = 6;
    //private final int PLAYER_TWO_KALAHA = 13;

    public KalahaController()
    {
        testframe = new JFrame();
        testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testframe.setLocationRelativeTo(null);

        pitpane = new PitPane(this);
        testframe.add(pitpane);
        testframe.setSize(800,200);
        testframe.setVisible(true);

        playerOne = new Player("foo", Player.ONE);
        playerTwo = new AI(Player.TWO);

        board = new Board();
        board.addObserver(pitpane);

        setUpBoard();

        activePlayer = playerOne;
        //for (int i=0; i<20; i++)
        //{
            //pitChosen(playerTwo.pickMove(board));
        //}

        //board.setSeeds(5,9);
        //board.setSeeds(0,0);
    }

    private void setUpBoard()
    {
        for (int pit : playerOne.getPits())
        {
            board.setSeeds(pit,3);
        }

        for (int pit : playerTwo.getPits())
        {
            board.setSeeds(pit,3);
        }

        board.setSeeds(playerOne.getKalaha(),0);
        board.setSeeds(playerTwo.getKalaha(),0);
    }

    public void pitChosen(int pit)
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
        if (board.getSeeds(pit) == 0)
        {
            return;
        }

        System.out.println(activePlayer.getName() + ": " + pit);

        int seeds = board.getSeeds(pit);
        board.setSeeds(pit,0);
        for (int i=1; i<=seeds; i++)
        {
            board.putSeed(pit + i);
        }

        // if the last seed is dropped into an empty pit belonging to the
        // active player, capture all seeds in the opposing pit
        int lastSeed = pit + seeds;
        if (board.getSeeds(lastSeed) == 1 &&
            Arrays.binarySearch(activePlayer.getPits(), lastSeed % board.NUM_PITS) >= 0)
        {
            int capturedSeeds = board.getSeeds(board.getOpposingPit(lastSeed));
            board.setSeeds(lastSeed, capturedSeeds+1);
            board.setSeeds(board.getOpposingPit(lastSeed),0);
        }

        if (endReached(board))
        {
            System.out.println("END");
            endGame(board);
        }
        // if the last seed dropped into the active player's kalaha,
        // they get another move. else, active status goes to the other
        // player.
        if (lastSeed % board.NUM_PITS != activePlayer.getKalaha())
        {
            if (activePlayer == playerOne)
            {
                activePlayer = playerTwo;
            }
            else if (activePlayer == playerTwo)
            {
                activePlayer = playerOne;
            }
            System.out.println(activePlayer.getName() + "\'s turn");
        }

        //if (activePlayer == playerOne)
        //{
            //activePlayer = playerTwo;
        //}
        //else if (activePlayer == playerTwo)
        //{
            //activePlayer = playerOne;
        //}

        while (activePlayer.getType() == PlayerType.AI)
        {
            pitChosen(((AI) activePlayer).pickMove(board));
        }
    }

    private boolean endReached(Board board)
    {
        int playerOneSeeds = 0;
        int playerTwoSeeds = 0;
        for (int pit : playerOne.getPits())
        {
            playerOneSeeds += board.getSeeds(pit);
        }
        for (int pit : playerTwo.getPits())
        {
            playerTwoSeeds += board.getSeeds(pit);
        }

        if (playerOneSeeds == 0 || playerTwoSeeds == 0)
        {
            return true;
        }
        return false;
    }

    private void endGame(Board board)
    {
        int playerOneSeeds = board.getSeeds(playerOne.getKalaha());
        int playerTwoSeeds = board.getSeeds(playerTwo.getKalaha());
        for (int pit : playerOne.getPits())
        {
            playerOneSeeds += board.getSeeds(pit);
            board.setSeeds(pit, 0);
        }
        for (int pit : playerTwo.getPits())
        {
            playerTwoSeeds += board.getSeeds(pit);
            board.setSeeds(pit, 0);
        }

        board.setSeeds(playerOne.getKalaha(), playerOneSeeds);
        board.setSeeds(playerTwo.getKalaha(), playerTwoSeeds);
    }
}
