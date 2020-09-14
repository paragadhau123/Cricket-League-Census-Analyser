import com.opencsv.bean.CsvBindByName;

public class IPLBowlersCSV {

    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName(column = "Mat")
    public int matchesPlayed;

    @CsvBindByName(column = "Inns")
    public int inningsPlayed;

    @CsvBindByName(column = "Ov")
    public double oversThrown;

    @CsvBindByName(column = "Runs")
    public int runsGiven;

    @CsvBindByName(column = "Wkts")
    public int wicketsTaken;

    @CsvBindByName(column = "BBI")
    public String bbI;

    @CsvBindByName(column = "Avg", required = true)
    public double avgOfBowler;

    @CsvBindByName(column = "Econ")
    public double economyOfBowler;

    @CsvBindByName(column = "SR")
    public double strikeRateOfBowler;

    @CsvBindByName(column = "4w")
    public int wickets4Taken;

    @CsvBindByName(column = "5w")
    public int wickets5Taken;

}
