import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class App {
    public static void spain(String[] args) {
        ChampionshipMaker championshipMaker = new ChampionshipMaker();
        ArrayList<Championship> list;
        try
        {
             list = championshipMaker.makeList("resource/realtime.csv");
             for (Championship championship: list) {
                 System.out.println(championship.getChampionship());
             }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCMaker jdbcMaker = new JDBCMaker();
        ArrayList<Championship> list;
        try {
            list = jdbcMaker.makeList("jdbc:relique:csv:C:\\Users\\Julien\\Desktop\\151\\csv\\app\\resource");
            for (Championship championship: list) {
                System.out.println(championship.getChampionship());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void porto(String[] args) {
        try
        {
            Class.forName("org.relique.jdbc.csv.CsvDriver");

            Properties props = new Properties();
            props.put("separator",";");
            props.put("suppressHeaders","false");
            props.put("fileExtension",".csv");

            Connection conn = DriverManager.getConnection("jdbc:relique:csv:C:\\Users\\Julien\\Desktop\\151\\csv\\app\\resource", props);

            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM realtime");

            ArrayList<Championship> championships = new ArrayList<Championship>();

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

            championships.stream().forEach(championship -> {
                System.out.println(championship.getBlub_idfs());
                System.out.println(championship.getCity());
            });

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
