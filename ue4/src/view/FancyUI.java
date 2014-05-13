package view;

import model.*;
import control.*;

import javax.swing.*;
import java.awt.event.*;

public class FancyUI implements ActionListener{
    private JFrame mainframe = new JFrame();
    private JPanel mainpanel = new JPanel();


    @Override
    public void actionPerformed(ActionEvent ae){

    }

    private SettingsPanel settingspanel = new SettingsPanel(this);
}
