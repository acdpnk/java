package control;

import javax.swing.JFrame;
import view.*;

public class KalahaController
{
    private JFrame testframe;
    private PitPane pitpane;

    public KalahaController()
    {
        testframe = new JFrame();
        testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testframe.setLocationRelativeTo(null);

        pitpane = new PitPane();
        testframe.add(pitpane);
        testframe.setSize(800,200);
        testframe.setVisible(true);


    }
}
