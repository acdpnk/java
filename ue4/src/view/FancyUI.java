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
    //private JMenuBar menubar;
    //private ActionListener supervisor;

    //public FancyUI(IStatisticController controller, String player, int maxrounds){
        //this.controller = controller;
        //this.player = player;
        //this.maxrounds = maxrounds;
    //}
    //public FancyUI(String filename, String player, int maxrounds){
        //this(new SimpleController(IO.readQuestions(filename)), player, maxrounds);
    //}
    //public FancyUI(){
        //this(new SimpleController(IO.readQuestions(STDFILE)),STDPLAYER, STDMAXROUNDS);
    //}
    public FancyUI(){
        this.frame = new JFrame();
        this.questionpanel = new QuestionPanel(this);
        this.settingspanel = new SettingsPanel(this);
        //this.supervisor = new ActionListener(){
            //public void actionPerformed(ActionEvent ae){
                //if (ae.source == settingspanel.goButton){
                    //this.controller = new SimpleController(
                                        //IO.readQuestions(
                                        //settingspanel.getQuestionFile()));

                //}
            //}
        //};
        frame.add(settingspanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void startGame(String questionFile, String player, int maxrounds){
        controller = new SimpleController(IO.readQuestions(questionFile));
        this.maxrounds = controller.getNumberOfQuestions() < maxrounds ? controller.getNumberOfQuestions() : maxrounds;
        this.player = player;
        frame.remove(settingspanel);
        frame.add(questionpanel);
        frame.pack();
        questionpanel.ask(controller.getQuestion());


        //for(int rounds = 1; rounds <= maxrounds; rounds++){
            //System.out.println("\n\nRunde " + rounds);
            //Question question = controller.getQuestion();
            //System.out.println(question.getQuestion());
            //for(int i=0; i<4; i++){
                //System.out.println("\t" + i + ") " + question.getAnswers()[i]);
            //}
            //System.out.println("\nBitte wählen Sie eine Antwort: ");
            //while(true){
                //int choice = -1;
                //try{
                    //choice = new java.util.Scanner(System.in).nextInt();
                    //System.out.println(controller.addDataSet(question, question.getAnswers()[choice]));
                //} catch (Exception e){
                    ////e.printStackTrace();
                //}
                //if(choice >= 0 && choice <= 3){
                    //break;
                //}
                //System.out.println("Bitte wählen Sie eine Zahl zwischen 0 und 3:");
            //}
        //}
        //System.out.println("\n\nRichtige Antworten:\t" + controller.getRightAnswers());
        //System.out.println("Falsche Antworten:\t" + controller.getWrongAnswers());
    }
    public void answer(Question question, String givenAnswer)
    {
        System.out.println(maxrounds);
        controller.addDataSet(question, givenAnswer);
        maxrounds--;
        if (maxrounds > 0)
        {
            questionpanel.ask(controller.getQuestion());
        }
        else
        {
            endGame();
        }
    }
    private void endGame()
    {
        IO.saveResult(controller, player);

    }
}
