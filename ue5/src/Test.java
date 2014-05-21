import javax.swing.*;
import java.awt.*;
import view.PitPane;

public class Test
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PitPane());
        frame.setVisible(true);
        frame.pack();
    }
}
