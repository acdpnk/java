package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Observer;
import java.util.Observable;
import view.*;
import model.*;

public class KalahaController extends MouseAdapter implements Observer
{
    private final JFrame mainframe;
    private KalahaPane kalahapane;
    private PitPane pitpane;
    private SettingsPane settingspane;
    private Board board;

    public KalahaController()
    {
        mainframe = new JFrame();

        settingspane = new SettingsPane(this);
        pitpane = new PitPane(this);
        kalahapane = new KalahaPane(this);

        kalahapane.add(pitpane, BorderLayout.CENTER);

        mainframe.setSize(800,201);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLocationRelativeTo(null);

        mainframe.add(settingspane);
        mainframe.repaint();

        mainframe.setVisible(true);

        mainframe.validate();
    }

    public void reset()
    {
        mainframe.remove(kalahapane);
        mainframe.add(settingspane, BorderLayout.CENTER);
        mainframe.repaint();
        mainframe.validate();

    }


    public void initializeGame(Player playerOne, Player playerTwo)
    {
        kalahapane.setPlayerOne(playerOne.getName());
        kalahapane.setPlayerTwo(playerTwo.getName());

        board = new Board(playerOne, playerTwo);
        board.addObserver(this);
        board.setUp();

        mainframe.remove(settingspane);
        mainframe.add(kalahapane);

        mainframe.validate();
        mainframe.repaint();

        Player openingPlayer = ((int) (Math.random()*2)) > 0 ? playerOne : playerTwo;
        System.out.println("\n\n*NEW GAME*\n" +
                           openingPlayer.getName() + " begins\n");
        board.setActivePlayer(openingPlayer);

    }

    public void finalizeGame(Player winner)
    {
        kalahapane.declareWinner(winner.getID());
    }

    public void pitChosen(int pit)
    {
        if(board.endReached())
        {
            reset();
        }
        else if (board.getActivePlayer().getType() == PlayerType.HUMAN)
        {
            System.out.println(board.getActivePlayer().getName()+ ": " + pit);
            board.move(pit);
        }
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
        else if (! board.endReached())
        {
            kalahapane.setActivePlayer(((Player) arg).getID());

            while (board.getActivePlayer().getType() == PlayerType.AI &&
            board.endReached() == false)
            {
                board.move(((AI) board.getActivePlayer()).pickMove(board));
            }
        }
        else
        {
            finalizeGame((Player) arg);
        }
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        if(board.endReached())
        {
            System.out.println("*click*");
            reset();
        }
    }
}
