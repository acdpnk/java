import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

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
                if (Arrays.asList(line).size() != 6){
                    // malformed entry
                    continue;
                }
                try {
                    solution = Integer.parseInt(line[5]);
                } catch (Exception e){
                    // malformed entry
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
}
