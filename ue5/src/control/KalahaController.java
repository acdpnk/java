package control;

import javax.swing.JFrame;
import java.util.Arrays;
import view.*;
import model.*;

public class KalahaController
{
    private JFrame testframe;
    private PitPane pitpane;
    private SettingsPane settingspane;
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;

    public KalahaController()
    {
        testframe = new JFrame();
        testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testframe.setLocationRelativeTo(null);

        settingspane = new SettingsPane(this);

        testframe.add(settingspane);
        testframe.setSize(800,200);
        testframe.setVisible(true);


        // playerOne = new Player("foo", Player.ONE);
        // playerTwo = new AI(Player.TWO);

        // board = new Board(playerOne, playerTwo);
        // board.addObserver(pitpane);

        // board.setUp();
        // board.setActivePlayer(playerOne);


    }


    public void initializeGame(Player playerOne, Player playerTwo)
    {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        pitpane = new PitPane(this);
        testframe.remove(settingspane);
        testframe.add(pitpane);
        testframe.setSize(800,200);

        board = new Board(playerOne, playerTwo);
        board.addObserver(pitpane);
        board.setUp();
        board.setActivePlayer(playerOne);
    }

    private void finalizeGame(Board board)
    {
        // TODO
    }

    public void pitChosen(int pit)
    {
        board.move(pit);
    }
}
