package control;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Iterator;

import model.Question;

public class SimpleController implements IStatisticController {
    private List<Question> questions;
    private Iterator<Question> qIterator;
    private HashMap<Question, String> answered;
    private int rounds, rightAnswers, wrongAnswers;

    public SimpleController(List<Question> questions){
        this.initialize(questions);
    }
    public SimpleController(){
        this(new ArrayList<Question>());
    }

    @Override
    public int getNumberOfQuestions(){
        return questions.size();
    }

    @Override
    public int getRightAnswers(){
        return this.rightAnswers;
    }

    @Override
    public int getWrongAnswers(){
        return this.wrongAnswers;
    }

    @Override
    public Map<Question, String> getAnswers(){
        return this.answered;
    }
    @Override
    public boolean addDataSet(Question question, String givenAnswer){
        this.rounds++;
        this.answered.put(question, givenAnswer);
        if(question.checkAnswer(givenAnswer)){
            this.rightAnswers++;
            return true;
        }
        this.wrongAnswers++;
        return false;
    }

    @Override
    public Question getQuestion(){
        if(qIterator.hasNext()){
            return qIterator.next();
        }
        return null;
    }

    @Override
    public void initialize(List<Question> questions){
        this.questions = questions;
        this.rightAnswers = 0;
        this.wrongAnswers = 0;
        this.rounds = 0;
        this.answered = new HashMap<Question, String>();
        List<Question> shuffled = new ArrayList<Question>(questions);
        Collections.shuffle(shuffled);
        this.qIterator = shuffled.iterator();
    }
}
