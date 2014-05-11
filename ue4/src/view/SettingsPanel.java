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
        this.roundsLabel = new JLabel("Number of Rounds per Game:");
        this.roundsField = new JTextField(Integer.toString(stdNumberOfRounds), 3);

        this.playerLabel = new JLabel("Player Name:");
        this.playerField = new JTextField(12);

        this.fileLabel = new JLabel("No Question File selected.");
        this.chooseFileButton = new JButton("Choose a Question File");

        this.goButton = new JButton("Go!");
        this.cancelButton = new JButton("Cancel");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.playerPanel = new JPanel();
        this.playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
        this.playerPanel.add(playerLabel);
        this.playerPanel.add(Box.createHorizontalGlue());
        this.playerPanel.add(playerField);
        this.add(playerPanel);

        this.roundsPanel = new JPanel();
        this.roundsPanel.setLayout(new BoxLayout(roundsPanel, BoxLayout.LINE_AXIS));
        this.roundsPanel.add(roundsLabel);
        this.playerPanel.add(Box.createHorizontalGlue());
        this.roundsPanel.add(roundsField);
        this.add(roundsPanel);

        this.filePanel = new JPanel();
        this.filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.LINE_AXIS));
        this.filePanel.add(fileLabel);
        this.filePanel.add(chooseFileButton);
        this.add(filePanel);

        this.goPanel = new JPanel();
        this.goPanel.setLayout(new BoxLayout(goPanel, BoxLayout.LINE_AXIS));
        this.goPanel.add(cancelButton);
        this.goPanel.add(goButton);
        this.add(goPanel);

        this.playerField.setColumns(12);
        this.roundsField.setColumns(3);
        this.setVisible(true);

        this.goButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

            }
        });

        this.chooseFileButton.addActionListener(new ActionListener(){
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
