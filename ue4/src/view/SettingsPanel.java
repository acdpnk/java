package view;

import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.io.File;

public class SettingsPanel extends JPanel{

    static final long serialVersionUID = 23; // this is here to stop javac from complaining.
    public static final int STD_NUMBER_OF_ROUNDS = 10;

    // gui elements
    private JLabel playerLabel, roundsLabel, fileLabel;
    private JPanel playerPanel, roundsPanel, filePanel, goPanel;
    private JTextField playerField, roundsField;
    private JButton chooseFileButton, goButton, cancelButton;

    // actual data
    private String questionFile = null;
    private String player = "";
    private int rounds = STD_NUMBER_OF_ROUNDS;

    public SettingsPanel(int stdNumberOfRounds){
        roundsLabel = new JLabel("Number of Rounds per Game:");
        roundsField = new JTextField(Integer.toString(stdNumberOfRounds), 3);

        playerLabel = new JLabel("Player Name:");
        playerField = new JTextField(12);

        fileLabel = new JLabel("No Question File selected.");
        chooseFileButton = new JButton("Choose a Question File");

        goButton = new JButton("Go!");
        cancelButton = new JButton("Cancel");

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
        playerPanel.add(playerLabel);
        playerPanel.add(Box.createHorizontalGlue());
        playerPanel.add(playerField);
        add(playerPanel);

        roundsPanel = new JPanel();
        roundsPanel.setLayout(new BoxLayout(roundsPanel, BoxLayout.LINE_AXIS));
        roundsPanel.add(roundsLabel);
        playerPanel.add(Box.createHorizontalGlue());
        roundsPanel.add(roundsField);
        add(roundsPanel);

        filePanel = new JPanel();
        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.LINE_AXIS));
        filePanel.add(fileLabel);
        filePanel.add(chooseFileButton);
        add(filePanel);

        goPanel = new JPanel();
        goPanel.setLayout(new BoxLayout(goPanel, BoxLayout.LINE_AXIS));
        goPanel.add(cancelButton);
        goPanel.add(goButton);
        add(goPanel);

        playerField.setColumns(12);
        roundsField.setColumns(3);
        setVisible(true);

        goButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

            }
        });

        chooseFileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(filePanel);
                File file = chooser.getSelectedFile();
                if(file == null){
                    return;
                }
                fileLabel.setText(file.getName());
                questionFile = file.getPath();
            }
        });


    }
    public SettingsPanel(){
        this(STD_NUMBER_OF_ROUNDS);
    }

}
