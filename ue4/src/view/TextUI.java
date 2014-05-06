package view;

import model.*;
import control.*;

public class TextUI {
    private static final int STDMAXROUNDS = 10;
    private static final String STDPLAYER = "Chris";
    private static final String STDFILE = "dummy.csv";

    private IStatisticController controller;
    private int maxrounds;
    private String player;

    public TextUI(IStatisticController controller, String player, int maxrounds){
        this.controller = controller;
        this.player = player;
        this.maxrounds = maxrounds;
    }
    public TextUI(String filename, String player, int maxrounds){
        this(new SimpleController(IO.readQuestions(filename)), player, maxrounds);
    }
    public TextUI(){
        this(new SimpleController(IO.readQuestions(STDFILE)),STDPLAYER, STDMAXROUNDS);
    }

    public void playGame(){
        maxrounds = controller.getNumberOfQuestions() < maxrounds ? controller.getNumberOfQuestions() : maxrounds;
        for(int rounds = 1; rounds <= maxrounds; rounds++){
            System.out.println("\n\nRunde " + rounds);
            Question question = controller.getQuestion();
            System.out.println(question.getQuestion());
            for(int i=0; i<4; i++){
                System.out.println("\t" + i + ") " + question.getAnswers()[i]);
            }
            System.out.println("\nBitte wählen Sie eine Antwort: ");
            while(true){
                int choice = -1;
                try{
                    choice = new java.util.Scanner(System.in).nextInt();
                    System.out.println(controller.addDataSet(question, question.getAnswers()[choice]));
                } catch (Exception e){
                    //e.printStackTrace();
                }
                if(choice >= 0 && choice <= 3){
                    break;
                }
                System.out.println("Bitte wählen Sie eine Zahl zwischen 0 und 3:");
            }
        }
        System.out.println("\n\nRichtige Antworten:\t" + controller.getRightAnswers());
        System.out.println("Falsche Antworten:\t" + controller.getWrongAnswers());
        IO.saveResult(controller, player);
    }
}
