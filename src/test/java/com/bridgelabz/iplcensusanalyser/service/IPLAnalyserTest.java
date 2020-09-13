package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.utility.SortField;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest extends TestCase {
    private static final String MOST_RUNS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\"
            + "Cricket-League-Census-Analyser\\src\\test\\resources\\Day12 Data_01 IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\"
            + "Cricket-League-Census-Analyser\\src\\test\\resources\\Day12 Data_02 IPL2019FactsheetMostWkts.csv";


    public void getBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BOWLER);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(99, count);
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_PlayersNameWhoHitsMaximum_SixesAndFours() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.SIXES_AND_FOURS, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Andre Russell", iplCSV[0].playerName);
    }

    @Test
    public void giveIPLMostRunsCSVFile_ShouldReturn_PlayerWith_TopStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKE_RATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Ishant Sharma", iplCSV[0].playerName);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_BestStrikingRatesWith4sAnd6s() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.FOURS_AND_SIXES_WITH_STRIKERATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Andre Russell", iplCSV[0].playerName);
    }
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_GreatAverageWithBestStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.AVERAGE_WITH_STRIKERATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("MS Dhoni", iplCSV[0].playerName);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHits_MaximumRunsWithBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.MAXIMUM_RUNS_WITH_BEST_AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("David Warner", iplCSV[0].playerName);
    }
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnCricketerWhoHas_BestBowlingAverage() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.BEST_BOWLING_AVERAGE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestStrikingRate() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKE_RATE, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestEconomy() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.ECONOMY, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplCSV[0].playerName);
    }
    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturnBowlerWhoHas_BestStrikeRateWith5wAnd4w() throws IPLAnalyserException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        String player = iplAnalyser.getSortedData(SortField.STRIKERATE_WITH_4W_AND_5W, true);
        IPLAnalyserDAO[] iplCSV = new Gson().fromJson(player, IPLAnalyserDAO[].class);
        Assert.assertEquals("Lasith Malinga", iplCSV[0].playerName);
    }
}