import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IPLAnalyserTest {

    private static final String IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH
            = "src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_2019_FACTSHEET_MOST_RUNS_CSV_INCORRECT_FILE_PATH
            = "src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String INCORRECT_IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH
            = "src/test/resources/IncorrectIPL2019FactsheetMostRuns.csv";
    private static final String NULL_VALUE_IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH
            = "src/test/resources/IncorrectIPL2019FactsheetMostRuns.csv";
    private static final String IPL_2019_FACTSHEET_BOWLING_STATS
            = "/home/slot1/IdeaProjects/IPLAnalyser/src/test/resources/IPL2019FactsheetMostWkts.csv";

    Map<String, IPLDAO> map;

    @Before
    public void setUp() {
        this.map = new HashMap<>();
    }

    @Test
    public void givenIPLMostRunsData_IfCorrectCSVFile_ShouldReturnCorrectRecords() {
        IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
        try {
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            System.out.println(map.size());
            Assert.assertEquals(100, map.size());
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsData_IfInCorrectCSVFilePath_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
        try {
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_INCORRECT_FILE_PATH);
        } catch (AnalyserException e) {
            Assert.assertEquals(AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_IfInCorrectCSV_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
        try {
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(INCORRECT_IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
        } catch (AnalyserException e) {
            Assert.assertEquals(AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_IfCorrectCSVButHasNullValues_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
        try {
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(NULL_VALUE_IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
        } catch (AnalyserException e) {
            Assert.assertEquals(AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_IfCorrectCSVButDataNotLoad_ShouldThrowException() {
        IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
        try {
            iplAnalyser.setAdapter(new BatsmanAdapter());
            iplAnalyser.loadIPLData(NULL_VALUE_IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
        } catch (AnalyserException e) {
            Assert.assertEquals(AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortedByAverage_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_AVERAGE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("MS Dhoni", csvs[99].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortedByStrikeRates_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_STRIKERATE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Ishant Sharma", csvs[99].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsData_IfDataNotLoadedForSortingStrikeRates_ShouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_STRIKERATE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
        } catch (AnalyserException e) {
            Assert.assertEquals(AnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortingForMost6sAnd4s_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_4s_AND_6s, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Andre Russell", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortingForMost6sAnd4sButDataNotLoad_ShouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_4s_AND_6s, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
        } catch (AnalyserException e) {
            Assert.assertEquals(AnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortingForMost6sAnd4sAndStrikingRates_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_STRIKERATE_WITHMOST_4sAND6s, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Andre Russell", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortingForMostAverageAndStrikingRates_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_AVERAGE_WITH_STRIKE_RATE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("MS Dhoni", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsData_IfSortingForMostAverageWithMostRuns_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_AVERAGE_WITH_MOST_RUNS, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("David Warner", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForMostAverageInBowlingStats_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BOWLER_STATS);
            iplAnalyser.setAdapter(new BowlingAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_TOP_BOWLING_AVERAGES, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Umesh Yadav", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForMostStrikeRateInBowlingStats_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BOWLER_STATS);
            iplAnalyser.setAdapter(new BowlingAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_TOP_BOWLING_STRIKING_RATES, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Suresh Raina", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForEconomyRateInBowlingStats_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BOWLER_STATS);
            iplAnalyser.setAdapter(new BowlingAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_TOP_BOWLING_ECONOMY_RATES, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Shivam Dube", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForMost4WsAnd5WsWithMostStrikingRate_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BOWLER_STATS);
            iplAnalyser.setAdapter(new BowlingAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_TOP_BOWLING_STRIKING_RATES_WITH_MOST_4Ws_AND_5Ws, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Kagiso Rabada", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForGreatAverageRateWithMostStrikingRate_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BOWLER_STATS);
            iplAnalyser.setAdapter(new BowlingAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_GREAT_AVERAGE_RATE_WITH_BEST_STRIKING_RATE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Suresh Raina", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForMostWicketsTakenGreatAverageRate_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BOWLER_STATS);
            iplAnalyser.setAdapter(new BowlingAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_MOST_WICKETS_WITH_GREAT_AVERAGE_RATE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Imran Tahir", csvs[0].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForBestBattingAverageAndBestAverageRate_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH, IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_MOST_BATTING_AVERAGE_WITH_MOST_BOWLING_AVERAGE, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Andre Russell", csvs[99].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBowlerData_IfSortingForMostBattingRunsAndMostBowlingWickets_ShouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(PlayerStats.BATTING_STATS);
            iplAnalyser.setAdapter(new BatsmanAdapter());
            map = iplAnalyser.loadIPLData(IPL_2019_FACTSHEET_MOST_RUNS_CSV_FILE_PATH, IPL_2019_FACTSHEET_BOWLING_STATS);
            String sortedResult = iplAnalyser.getFieldWiseSortedIPLData
                    (FieldsToSort.BY_MOST_BATTING_RUNS_WITH_MOST_BOWLING_WICKETS, map);
            IPLDAO[] csvs = new Gson().fromJson(sortedResult, IPLDAO[].class);
            Assert.assertEquals("Hardik Pandya", csvs[99].playerName);
        } catch (AnalyserException e) {
            e.printStackTrace();
        }
    }
}