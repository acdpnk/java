package control;

import javax.swing.JFrame;
import view.*;
import model.*;

public class KalahaController
{
    private JFrame testframe;
    private PitPane pitpane;
    private Board board;

    private final int[] PLAYER_ONE_PITS = {0,1,2,3,4,5};
    private final int[] PLAYER_TWO_PITS = {7,8,9,10,11,12};
    private final int PLAYER_ONE_KALAHA = 6;
    private final int PLAYER_TWO_KALAHA = 13;

    public KalahaController()
    {
        testframe = new JFrame();
        testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testframe.setLocationRelativeTo(null);

        pitpane = new PitPane(this);
        testframe.add(pitpane);
        testframe.setSize(800,200);
        testframe.setVisible(true);

        board = new Board();
        board.addObserver(pitpane);

        setUpBoard();

    }

    private void setUpBoard()
    {
        for (int pit : PLAYER_ONE_PITS)
        {
            board.setSeeds(pit,3);
        }

        for (int pit : PLAYER_TWO_PITS)
        {
            board.setSeeds(pit,3);
        }

        board.setSeeds(PLAYER_ONE_KALAHA,0);
        board.setSeeds(PLAYER_TWO_KALAHA,0);
    }

    public void pitChosen(int pit)
    {
        int seeds = board.getSeeds(pit);
        System.out.println("seeds: " + seeds);
        board.setSeeds(pit,0);
        for (int i=1; i<=seeds; i++)
        {
            board.putSeed(pit + i);
            System.out.println(pit+i);
        }
    }
}
