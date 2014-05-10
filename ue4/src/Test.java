import javax.swing.*;
import java.awt.event.*;
import view.SettingsPanel;

public class Test{
    public static void main(String[] args){
        final JFrame mainframe = new JFrame();
        JPanel mainpanel = new SettingsPanel(10);
        mainframe.add(mainpanel);
        mainframe.pack();
        mainframe.setLocationRelativeTo(null);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }
}

