import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ChampionshipMaker {
    ArrayList<Championship> championships;

    public ArrayList<Championship> makeList(String url) throws IOException{
        String line = "";
        String splitBy = ";";

        this.championships = new ArrayList<Championship>();
        try {
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(url));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] employee = line.split(splitBy);
                if (employee.length > 1) {
                    Championship championship = new Championship();
                    championship.setBlub_idfs(employee[0]);
                    championship.setId(employee[1]);
                    championship.setYear(employee[2]);
                    championship.setDate_from(employee[3]);
                    championship.setDate_to(employee[4]);
                    championship.setChampionship(employee[5]);
                    championship.setCity(employee[6]);
                    championship.setCountry(employee[7]);
                    championship.setEvent_date(employee[8]);
                    championship.setEvent_time_from(employee[9]);
                    championship.setEvent_time_to(employee[10]);
                    championship.setEvent_title(employee[11]);

                    this.championships.add(championship);
                }
                // System.out.println(Arrays.toString(employee));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.championships;
    }
}
