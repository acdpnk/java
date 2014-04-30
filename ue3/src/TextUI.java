public class TextUI {
    private static final int MAXROUNDS = 10;
    private static final String PLAYER = "Chris";
    private static final String FILE = "dummy.csv";

    private IStatisticController controller;

    public TextUI(IStatisticController controller){
        this.controller = controller;
        //this.rounds = controller.getNumberOfQuestions() < rounds ? controller.getNumberOfQuestions() : rounds;
    }
    public TextUI(){
        this(new SimpleController(IO.readQuestions(FILE)));
    }

    public void playGame(){
        int maxRounds = controller.getNumberOfQuestions() < MAXROUNDS ? controller.getNumberOfQuestions() : MAXROUNDS;
        for(int rounds = 1; rounds <= maxRounds; rounds++){
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
                    e.printStackTrace();
                }
                if(choice >= 0 && choice <= 3){
                    break;
                }
                System.out.println("Bitte wählen Sie eine Zahl zwischen 0 und 3:");
            }
        }
        System.out.println("\n\nRichtig:\t" + controller.getRightAnswers());
        System.out.println("Falsch:\t" + controller.getWrongAnswers());
        IO.saveResult(controller, PLAYER);
    }
}
