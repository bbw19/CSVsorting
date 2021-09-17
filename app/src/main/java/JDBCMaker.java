import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCMaker {
    public ArrayList<Championship> championships;

    public ArrayList<Championship> makeList(String url) throws Exception
    {
        try
        {
            Class.forName("org.relique.jdbc.csv.CsvDriver");

            Properties props = new Properties();
            props.put("separator",";");
            props.put("suppressHeaders","false");
            props.put("fileExtension",".csv");

            Connection conn = DriverManager.getConnection(url, props);

            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM realtime");

            this.championships = new ArrayList<Championship>();

            while (results.next()) {
                System.out.println("id= "+results.getString("id"));
                System.out.println("blub_idfs" + results.getString("blub_idfs"));
                Championship championship = new Championship();
                championship.setBlub_idfs(results.getString("blub_idfs"));
                championship.setId(results.getString("id"));
                championship.setYear(results.getString("year"));
                championship.setDate_from(results.getString("date_from"));
                championship.setDate_to(results.getString("date_to"));
                championship.setChampionship(results.getString("championship"));
                championship.setCity(results.getString("city"));
                championship.setCountry(results.getString("country"));
                championship.setEvent_date(results.getString("event_date"));
                championship.setEvent_time_from(results.getString("event_time_from"));
                championship.setEvent_time_to(results.getString("event_time_to"));
                championship.setEvent_title(results.getString("event_title"));

                championships.add(championship);
            }

            results.close();
            stmt.close();
            conn.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return championships;
    }
}
