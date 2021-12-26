import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {
    private Connection con;
    private Statement statement;
    private boolean SqlCreated = false;
    private final Map<String, Integer> groups = new HashMap<String, Integer>();
    private final Map<String, Integer> series = new HashMap<String, Integer>();
    private final Map<String, Integer> subjects = new HashMap<String, Integer>();
    private final Map<String, Integer> units = new HashMap<String, Integer>();
    private final Map<Integer, Integer> magnitudes = new HashMap<Integer, Integer>();
    private final Map<String, Integer> statuses = new HashMap<String, Integer>();
    private final Map<String, Integer> supressed = new HashMap<String, Integer>();
    private int seriesID = 1;
    private int subjectsID = 1;
    private int unitsID = 1;
    private int groupID = 1;
    private int paymentsID = 1;
    private int magnitudesID = 1;
    private int statusID = 1;
    private int suppressedID = 1;


    public CsvParser() {
    }


    private void connect() {
        try {
            String url = "jdbc:sqlite:D:/GitHubRepositories/untitled1/transfers.sqlite";
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            statement = con.createStatement();

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void MakeSql(String fileName) {
        connect();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            r.stream().skip(1).forEach(x -> {
                try {
                    AppendSql(createCsvRow(x));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public CsvRow createCsvRow(String[] x) {
        var date = x[1].split("\\.");
        double value;
        if (String.valueOf(x[2]).isEmpty()) {
            value = 0.0;
        } else {
            value = Double.parseDouble(x[2]);
        }
        var result = new CsvRow(x[0], Integer.parseInt(date[0]), Integer.parseInt(date[1]), value,
                x[3], x[4], x[5], Integer.parseInt(x[6]), x[7], x[8], x[9], x[10], x[11]);
        return result;
    }

    public void AppendSql(CsvRow input) throws SQLException {
        if (!SqlCreated) {
            createSqlTables();
        }
        InsertData(input);
    }

    public void createSqlTables() throws SQLException {
        statement.execute("drop table if exists payments");
        statement.execute("create table if not exists payments (paymentID INTEGER PRIMARY KEY ," +
                " month INTEGER, year INTEGER, value DOUBLE)");

        statement.execute("drop table if exists paymentInfo");
        statement.execute("create table if not exists paymentInfo (paymentID INTEGER REFERENCES payments(paymentID)" +
                ", unitID INTEGER REFERENCES units(unitID), magnitudeID INTEGER REFERENCES magnitudes(magnitudeID)," +
                " statusID  INTEGER REFERENCES paymentStatus(statusID), seriesID INTEGER  REFERENCES series(seriesID), " +
                "supressedID INTEGER REFERENCES supressed(supressedID))");

        statement.execute("drop table if exists units");
        statement.execute("create table if not exists units (unitID INTEGER PRIMARY KEY, unit TEXT)");
        statement.execute("drop table if exists magnitudes");
        statement.execute("create table if not exists magnitudes (magnitudeID INTEGER PRIMARY KEY, magnitude INTEGER)");

        statement.execute("drop table if exists paymentStatus");
        statement.execute("create table if not exists paymentStatus (statusID INTEGER PRIMARY KEY , status TEXT)");

        statement.execute("drop table if exists supressed");
        statement.execute("create table if not exists supressed (supressedID INTEGER PRIMARY KEY ," +
                " supressed TEXT)");

        statement.execute("drop table if exists seires");
        statement.execute("create table if not exists seires (seriesID INTEGER PRIMARY KEY ," +
                " series TEXT, t1 TEXT, t2 TEXT, t3 TEXT, subID INTEGER  REFERENCES subject(subID), groupID INTEGER" +
                "  REFERENCES grups(groupID))");
        statement.execute("drop table if exists subject");
        statement.execute("create table if not exists subject (subID INTEGER PRIMARY KEY ," +
                " subject TEXT)");
        statement.execute("drop table if exists grups");
        statement.execute("create table if not exists grups (groupID INTEGER PRIMARY KEY ," +
                " groupa TEXT)");
        SqlCreated = true;
    }

    public void InsertData(CsvRow input) throws SQLException {
        var paymentData = "INSERT INTO payments(paymentID, month, year , value ) VALUES(?,?,?,?)";
        var pstmt = con.prepareStatement(paymentData);
        pstmt = con.prepareStatement(paymentData);
        pstmt.setInt(2, paymentsID);
        pstmt.setInt(2, input.month);
        pstmt.setInt(3, input.year);
        pstmt.setDouble(4, input.dataValue);
        pstmt.executeUpdate();


        if (!groups.containsKey(input.group)) {
            groups.put(input.group, groupID);
            String groupData = "INSERT INTO grups(groupID, groupa) VALUES(?,?) ";
            pstmt = con.prepareStatement(groupData);
            pstmt.setString(2, input.group);
            pstmt.setInt(1, groupID);
            pstmt.executeUpdate();
            groupID++;
        }
        if (!subjects.containsKey(input.subject)) {
            subjects.put(input.subject, subjectsID);
            var subData = "INSERT INTO subject(subID, subject) VALUES(?,?)";
            pstmt = con.prepareStatement(subData);
            pstmt.setInt(1, subjectsID);
            pstmt.setString(2, input.subject);
            pstmt.executeUpdate();
            subjectsID++;
        }
        if (!series.containsKey(input.seriesReference)) {
            series.put(input.seriesReference, seriesID);
            var seriesData = "INSERT INTO seires(seriesID, series, t1, t2, t3, subID, groupID) VALUES(?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(seriesData);
            pstmt.setInt(1, seriesID);
            pstmt.setString(2, input.seriesReference);
            pstmt.setString(3, input.seriesTitle1);
            pstmt.setString(4, input.seriesTitle2);
            pstmt.setString(5, input.seriesTitle3);
            pstmt.setInt(6, subjects.get(input.subject));
            pstmt.setInt(7, groups.get(input.group));
            pstmt.executeUpdate();
            seriesID++;
        }
        if (!units.containsKey(input.units)) {
            units.put(input.units, unitsID);
            var unitData = "INSERT INTO units(unitID, unit) VALUES(?,?)";
            pstmt = con.prepareStatement(unitData);
            pstmt.setInt(1, unitsID);
            pstmt.setString(2, input.units);
            pstmt.executeUpdate();
            unitsID++;
        }
        if (!magnitudes.containsKey(input.magnitude)) {
            magnitudes.put(input.magnitude, magnitudesID);
            var magnitudeData = "INSERT INTO magnitudes(magnitudeID, magnitude) VALUES(?,?)";
            pstmt = con.prepareStatement(magnitudeData);
            pstmt.setInt(1, magnitudesID);
            pstmt.setInt(2, input.magnitude);
            pstmt.executeUpdate();
            magnitudesID++;
        }
        if (!supressed.containsKey(input.suppressed)) {
            supressed.put(input.suppressed, suppressedID);
            var payData = "INSERT INTO supressed(supressedID , supressed ) VALUES(?,?)";
            pstmt = con.prepareStatement(payData);
            pstmt.setInt(1, suppressedID);
            pstmt.setString(2, input.suppressed);
            pstmt.executeUpdate();
            suppressedID++;
        }
        if (!statuses.containsKey(input.status)) {
            statuses.put(input.status, statusID);
            var payData = "INSERT INTO paymentStatus(statusID , status) VALUES(?,?)";
            pstmt = con.prepareStatement(payData);
            pstmt.setInt(1, statusID);
            pstmt.setString(2, input.status);
            pstmt.executeUpdate();
            statusID++;
        }

        var info = "INSERT INTO paymentInfo(paymentID, unitID, magnitudeID, statusID, supressedID, seriesID) VALUES(?,?,?,?,?,?)";
        pstmt = con.prepareStatement(info);
        pstmt.setInt(1, paymentsID);
        pstmt.setInt(2, units.get(input.units));
        pstmt.setInt(3, magnitudes.get(input.magnitude));
        pstmt.setInt(4, statuses.get(input.status));
        pstmt.setInt(5, supressed.get(input.suppressed));
        pstmt.setInt(6, series.get(input.seriesReference));
        pstmt.executeUpdate();
        paymentsID++;
    }
}




