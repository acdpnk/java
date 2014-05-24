package view;
import javax.swing.*;
import java.awt.*;
import control.KalahaController;
import model.Player;

public class KalahaPane extends JPanel
{
    private JLabel playeronelabel, playertwolabel;

    public KalahaPane(KalahaController controller)
    {
        super();

        playeronelabel = new JLabel("",SwingConstants.CENTER);
        playeronelabel.addMouseListener(controller);
        playertwolabel = new JLabel("",SwingConstants.CENTER);
        playertwolabel.addMouseListener(controller);

        setLayout(new BorderLayout());

        add(playertwolabel, BorderLayout.PAGE_START);
        add(playeronelabel, BorderLayout.PAGE_END);

    }

    public void setPlayerOne(String name)
    {
        playeronelabel.setText(name);
    }
    public void setPlayerTwo(String name)
    {
        playertwolabel.setText(name);
    }

    public void setActivePlayer(int playerID)
    {

        if(playerID == Player.ONE)
        {
            playeronelabel.setForeground(Color.RED);
            playertwolabel.setForeground(Color.BLACK);
        }
        if(playerID == Player.TWO)
        {
            playertwolabel.setForeground(Color.RED);
            playeronelabel.setForeground(Color.BLACK);
        }
    }

}