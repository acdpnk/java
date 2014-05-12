import javax.swing.*;
import java.awt.event.*;
import view.SettingsPanel;

public class Test{
    public static void main(String[] args){

        final JFrame mainframe = new JFrame();
        final JPanel mainpanel = new JPanel();
        final JPanel secondpanel = new JPanel();

        ActionListener supervisor = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if (ae.getSource() instanceof JButton){
                    JButton source = (JButton) ae.getSource();
                    if( ! source.getText().equals("Go!")){return;}
                    mainframe.remove(mainpanel);
                    mainframe.add(secondpanel);
                    mainframe.pack();
                }
            }
        };

        JPanel settingspanel = new SettingsPanel(supervisor);

        mainframe.add(mainpanel);
        mainpanel.add(settingspanel);
        mainframe.pack();
        mainframe.setLocationRelativeTo(null);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);

    }
}

