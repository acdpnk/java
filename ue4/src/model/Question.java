package model;

public class Question {
    private String question;
    private String[] answers;
    private int solution;

    public Question(String question, String[] answers, int solution){
        this.question = question;
        this.answers = answers;
        this.solution = solution;
    }

    public String getQuestion(){
        return this.question;
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public boolean checkAnswer(String answer){
        String solution = this.answers[this.solution];
        if(solution.equalsIgnoreCase(answer)){
            return true;
        }
        return false;
    }

    public int getSolution(){
        return this.solution;
    }
}
