package control;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Map;

import model.Question;
public class IO {
    public static List<Question> readQuestions(String filename){
        ArrayList<Question> questions = new ArrayList<Question>();
        int solution;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filename));

            String l = br.readLine(); // throw away the header

            while((l = br.readLine()) != null ){
                String[] line = l.split(",");
                if (line.length != 6){
                    // malformed entry
                    continue;
                }
                for(int i=0; i<line.length; i++){
                    line[i] = line[i].trim();
                }
                try {
                    solution = Integer.parseInt(line[5].trim());
                } catch (Exception e){
                    // malformed entry
                    e.printStackTrace();
                    continue;
                }
                questions.add(new Question(line[0], Arrays.copyOfRange(line, 1, 5), solution));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return questions;
    }


    public static void saveResultAs(String filename, IStatisticController controller, String playername){
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

            bw.write("Player Name: " + playername);
            bw.newLine();
            bw.write("Richtige Antworten:\t" + controller.getRightAnswers());
            bw.newLine();
            bw.write("Falsche Antworten:\t" + controller.getWrongAnswers());

            bw.newLine();
            bw.newLine();

            Map<Question, String> answers = controller.getAnswers();

            for(Map.Entry<Question, String> entry : answers.entrySet()){
                bw.write(entry.getKey().getQuestion() + ", " +
                         entry.getValue() + ", " +
                         entry.getKey().checkAnswer(entry.getValue()));
                bw.newLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void saveResult(IStatisticController controller, String playername)
    {
        String filename = playername + "_" + System.currentTimeMillis();
        saveResultAs(filename, controller, playername);
    }
}
