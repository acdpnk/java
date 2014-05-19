package view;

import model.*;
import control.*;
import java.awt.*;
import javax.swing.*;

public class FancyUI {
    private static final int STDMAXROUNDS = 10;
    private static final String STDPLAYER = "name";
    private static final String STDFILE = "dummy.csv";

    private IStatisticController controller;
    private int maxrounds;
    private String player;

    // ui elements
    private JFrame frame;
    private SettingsPanel settingspanel;
    private QuestionPanel questionpanel;
    private ResultPanel   resultpanel;

    public FancyUI(){
        try
        {
            // i hate that i have to do this, but apparently this is the
            // only way to make the oh-so-platform-agnostic java
            // behave consistent over multiple operating systems without
            // bending over backwards. yes, it's butt-ugly. go figure.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        this.frame = new JFrame();
        this.questionpanel = new QuestionPanel(this);
        this.settingspanel = new SettingsPanel(this);
        this.resultpanel   = new ResultPanel(this);

        setup();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void setup()
    {
        // go into setup mode, with settings displayed
        frame.getContentPane().removeAll();
        frame.add(settingspanel);
        frame.pack();
    }

    public void startGame(String questionFile, String player, int maxrounds)
    {
        controller = new SimpleController(IO.readQuestions(questionFile));
        this.maxrounds = controller.getNumberOfQuestions() < maxrounds ? controller.getNumberOfQuestions() : maxrounds;
        this.player = player;
        frame.remove(settingspanel);
        frame.add(questionpanel);
        frame.setSize(700,350);
        questionpanel.ask(controller.getQuestion());

    }
    public void answer(Question question, String givenAnswer)
    // gets called by the QuestionPanel
    {
        controller.addDataSet(question, givenAnswer);
        maxrounds--;
    }
    public void endGame()
    //gets called by the QuestionPanel
    {
        IO.saveResult(controller, player);
        frame.remove(questionpanel);
        frame.add(resultpanel);
        resultpanel.display(player, controller);
        frame.pack();
    }
    public Question getQuestion()
    {
        return controller.getQuestion();
    }
    public IStatisticController getController()
    {
        return this.controller;
    }
    public int getMaxRounds()
    {
        return maxrounds;
    }
}