import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        var parser = new CsvParser();
        parser.MakeSql("D:\\GitHubRepositories\\untitled1\\Transfers.csv");
    }

}
