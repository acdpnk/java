package view;

import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

public class SettingsPanel extends JPanel{
    static final long serialVersionUID = 23;
    private JLabel playerLabel, roundsLabel, fileLabel;
    private JPanel playerPanel, roundsPanel, filePanel, goPanel;
    private JTextField playerField, roundsField;
    private JButton chooseFileButton, goButton;

    public SettingsPanel(int stdNumberOfRounds){
        this.roundsLabel = new JLabel("Number of Rounds per Game:");
        this.roundsField = new JTextField(Integer.toString(stdNumberOfRounds));

        this.playerLabel = new JLabel("Player Name:");
        this.playerField = new JTextField();

        this.fileLabel = new JLabel("No Question File selected.");
        this.chooseFileButton = new JButton("Choose a Question File");

        this.goButton = new JButton("Go!");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.playerPanel = new JPanel();
        this.playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
        this.playerPanel.add(playerLabel);
        this.playerPanel.add(playerField);
        this.add(playerPanel);

        this.roundsPanel = new JPanel();
        this.roundsPanel.setLayout(new BoxLayout(roundsPanel, BoxLayout.LINE_AXIS));
        this.roundsPanel.add(roundsLabel);
        this.roundsPanel.add(roundsField);
        this.add(roundsPanel);

        this.filePanel = new JPanel();
        this.filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.LINE_AXIS));
        this.filePanel.add(fileLabel);
        this.filePanel.add(chooseFileButton);
        this.add(filePanel);

        this.goPanel = new JPanel();
        this.goPanel.setLayout(new BoxLayout(goPanel, BoxLayout.LINE_AXIS));
        this.goPanel.add(goButton);
        this.add(goPanel);

        this.setVisible(true);

    }

}
