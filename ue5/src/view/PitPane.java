package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PitPane extends JPanel implements ActionListener
{
    private JButton[] pits;

    public PitPane()
    {
        super();
        setSize(200,800);
        setLayout(new BorderLayout());
        pits = new JButton[14];

        JPanel centerPane = new JPanel();
        centerPane.setLayout(new GridLayout(2,6));

        for (int i=0; i<14; i++)
        {
            pits[i] = new JButton(Integer.toString(i));
            pits[i].addActionListener(this);
            if (! (i % 7 == 0))
            {
                centerPane.add(pits[i]);
            }
        }

        add(pits[0], BorderLayout.LINE_START);
        add(pits[7], BorderLayout.LINE_END);
        add(centerPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println(((JButton) ae.getSource()).getText());
    }
}
