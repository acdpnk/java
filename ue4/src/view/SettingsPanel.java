package view;

import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.event.*;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.File;

public class SettingsPanel extends JPanel{

    static final long serialVersionUID = 23; // this is here to stop javac from complaining.
    public static final int STD_NUMBER_OF_ROUNDS = 10;
    public static final String STD_PLAYERNAME = "name";

    // gui elements
    private JLabel playerLabel, roundsLabel, fileLabel;
    private JPanel playerPanel, roundsPanel, filePanel, goPanel;
    private JTextField playerField, roundsField;
    private JButton chooseFileButton, goButton, cancelButton;
    private ActionListener supervisor;

    // actual data
    private String questionFile = null;
    private String player = "";
    private int rounds = STD_NUMBER_OF_ROUNDS;

    public SettingsPanel(ActionListener supervisor, String stdPlayerName, int stdNumberOfRounds){
        this.supervisor = supervisor;
        roundsLabel = new JLabel("Number of Rounds per Game:");
        roundsField = new JTextField(Integer.toString(stdNumberOfRounds), 3);
        roundsField.getDocument().addDocumentListener(updateListener);

        playerLabel = new JLabel("Player Name:");
        playerField = new JTextField(stdPlayerName, 12);
        playerField.getDocument().addDocumentListener(updateListener);

        fileLabel = new JLabel("No Question File selected.");
        chooseFileButton = new JButton("Choose a Question File");

        goButton = new JButton("Go!");
        goButton.addActionListener(supervisor);
        goButton.setEnabled(false);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(supervisor);

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
                goButton.setEnabled(checkReady());
            }
        });


    }
    public SettingsPanel(ActionListener supervisor){
        this(supervisor, STD_PLAYERNAME, STD_NUMBER_OF_ROUNDS);
    }
    public SettingsPanel(ActionListener supervisor, int stdNumberOfRounds){
        this(supervisor, STD_PLAYERNAME, stdNumberOfRounds);
    }

    private boolean checkReady(){
        if (questionFile == null){
            return false;
        }
        if (playerField.getText().equals("")){
            return false;
        }
        try {
            rounds = Integer.parseInt(roundsField.getText());
        } catch(Exception e) {
            return false;
        }
        if (rounds <= 0 ){
            return false;
        }

        return true;
    }
    //private void update(){
        //goButton.setEnabled(checkReady());
    //}

    private DocumentListener updateListener = new DocumentListener(){
        public void changedUpdate(DocumentEvent de){
            goButton.setEnabled(checkReady());
        }
        public void removeUpdate(DocumentEvent de){
            goButton.setEnabled(checkReady());
        }
        public void insertUpdate(DocumentEvent de){
            goButton.setEnabled(checkReady());
        }
    };
}
