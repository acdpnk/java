package control;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Observer;
import java.util.Observable;
import view.*;
import model.*;

public class KalahaController implements Observer
{
    private JFrame mainframe;
    private JPanel mainpane;
    private JLabel playeronelabel, playertwolabel;
    private PitPane pitpane;
    private SettingsPane settingspane;
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;

    public KalahaController()
    {
        mainframe = new JFrame();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLocationRelativeTo(null);

        mainpane = new JPanel();
        mainframe.add(mainpane);

        mainpane.setLayout(new BorderLayout());

        settingspane = new SettingsPane(this);
        pitpane = new PitPane(this);

        playeronelabel = new JLabel("",SwingConstants.CENTER);
        playertwolabel = new JLabel("",SwingConstants.CENTER);

        mainpane.add(settingspane, BorderLayout.CENTER);
        mainframe.setSize(800,200);
        mainframe.setVisible(true);


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

        playeronelabel.setText(playerOne.getName());
        playertwolabel.setText(playerTwo.getName());

        mainpane.removeAll();
        mainpane.add(pitpane, BorderLayout.CENTER);
        mainpane.add(playertwolabel, BorderLayout.PAGE_START);
        mainpane.add(playeronelabel, BorderLayout.PAGE_END);
        mainpane.setSize(800,300);

        board = new Board(playerOne, playerTwo);
        board.addObserver(this);
        board.setUp();
        board.setActivePlayer(playerOne);
    }

    public void finalizeGame(Board board)
    {
        // TODO
    }

    public void pitChosen(int pit)
    {
        board.move(pit);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(!(arg instanceof Player))
        {
            int pit = (Integer) arg;
            int seeds = ((Board) o).getSeeds(pit);

            pitpane.setPit(pit, seeds);
        }
        else
        {
            //TODO (player update)
        }
    }
}
