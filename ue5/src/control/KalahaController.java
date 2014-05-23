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

        board = new Board(playerOne, playerTwo);
        board.addObserver(pitpane);

        board.setUp();
        board.setActivePlayer(playerOne);


    }

    public void pitChosen(int pit)
    {
        board.move(pit);
    }
}
