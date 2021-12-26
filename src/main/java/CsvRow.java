

public class CsvRow {
    public String seriesReference;
    public int month;
    public int year;
    public double dataValue;
    public String suppressed;
    public String status;
    public String units;
    public int magnitude;
    public String subject;
    public String group;
    public String seriesTitle1;
    public String seriesTitle2;
    public String seriesTitle3;


    public CsvRow(String seriesReference,  int year, int month, double dataValue, String suppressed,
                  String status, String units, int magnitude, String subject, String group,
                  String seriesTitle1, String seriesTitle2, String seriesTitle3) {
        this.seriesReference = seriesReference;
        this.year = year;
        this.month = month;
        this.dataValue = dataValue;
        this.suppressed = suppressed;
        this.status = status;
        this.units = units;
        this.magnitude = magnitude;
        this.subject = subject;
        this.group = group;
        this.seriesTitle1 = seriesTitle1;
        this.seriesTitle2 = seriesTitle2;
        this.seriesTitle3 = seriesTitle3;
    }
}
