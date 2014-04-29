public interface IStatisticController {
    public void initialize(List<Question> questions);
    public boolean addDataSet(Question question, String givenAnswer);
    public Question getQuestion();
    public int getNumberOfQuestions();
    public int getRightAnswers();
    public int getWrongAnswers();
    public Map<Question, String> getAnswers();
}
