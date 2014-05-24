package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.KalahaController;
import model.Player;
import model.AI;

public class SettingsPane extends JPanel implements ActionListener
{
    private KalahaController controller;
    private JCheckBox playerOneAI, playerTwoAI;
    private JTextField playerOneName, playerTwoName;
    private JButton goButton;
    public SettingsPane(KalahaController controller)
    {
        this.controller = controller;
        playerOneAI = new JCheckBox("AI");
        playerTwoAI = new JCheckBox("AI");
        playerTwoAI.setSelected(true);
        playerOneName = new JTextField("Player 1");
        playerTwoName = new JTextField("Player 2");
        goButton = new JButton("go");

        setLayout(new BorderLayout());

        add(playerTwoName, BorderLayout.PAGE_START);
        add(playerTwoAI, BorderLayout.PAGE_START);
        add(playerOneName, BorderLayout.CENTER);
        add(playerOneAI, BorderLayout.CENTER);
        add(goButton, BorderLayout.PAGE_END);

        goButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Player playerOne, playerTwo;

        if(playerOneName.getText() == "")
        {
            playerOneName.setText("Player 1");
        }
        if(playerTwoName.getText() == "")
        {
            playerTwoName.setText("Player 2");
        }

        if(playerOneAI.isSelected())
        {
            playerOne = new AI(playerOneName.getText(), Player.ONE);
        }
        else
        {
            playerOne = new Player(playerOneName.getText(), Player.ONE);
        }

        if(playerTwoAI.isSelected())
        {
            playerTwo = new AI(playerTwoName.getText(), Player.TWO);
        }
        else
        {
            playerTwo = new Player(playerTwoName.getText(), Player.TWO);
        }

        controller.initializeGame(playerOne, playerTwo);
    }
}