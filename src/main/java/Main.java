import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        var parser = new CsvParser();
        parser.MakeSql("D:\\GitHubRepositories\\untitled1\\Transfers.csv");
    }
}
