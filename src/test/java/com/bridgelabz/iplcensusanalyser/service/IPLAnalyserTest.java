package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLMostRunsCSV;
import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest extends TestCase {
    private static final String MOST_RUNS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\"
        +"Cricket-League-Census-Analyser\\src\\test\\resources\\Day12 Data_01 IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "D:\\parag\\Fellowship\\"
        +"Cricket-League-Census-Analyser\\src\\test\\resources\\Day12 Data_02 IPL2019FactsheetMostWkts.csv";


    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, count);
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
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
    public void giveIPLMostRunsCSVFile_ShouldReturn_PlayerWith_TopBattingAverage() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            IPLMostRunsCSV bestBattingAveragePlayer = iplAnalyser.getTopBattingAveragePlayer();
            Assert.assertThat(bestBattingAveragePlayer.player, CoreMatchers.is("MS Dhoni"));
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_PlayersNameWhoHitsMaximum_SixesAndFours() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            IPLMostRunsCSV maximumFourHitter = iplAnalyser.getMaximumFourHitter();
            IPLMostRunsCSV maximumSixHitter = iplAnalyser.getMaximumSixHitter();
            Assert.assertThat(maximumFourHitter.player, CoreMatchers.is("Shikhar Dhawan"));
            Assert.assertThat(maximumSixHitter.player, CoreMatchers.is("Andre Russell"));
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
    @Test
    public void giveIPLMostRunsCSVFile_ShouldReturn_PlayerWith_TopStrikeRate() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            IPLMostRunsCSV bestBattingAveragePlayer = iplAnalyser.getTopStrikeRatePlayer();
            Assert.assertThat(bestBattingAveragePlayer.player, CoreMatchers.is("Ishant Sharma"));
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturnCricketerWhoHad_BestStrikingRatesWith4sAnd6s() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN);
            iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
            IPLMostRunsCSV maximumFourHitter = iplAnalyser.getMaximumFourHitter();
            IPLMostRunsCSV maximumSixHitter = iplAnalyser.getMaximumSixHitter();
            IPLMostRunsCSV bestPlayerWithStrikeRate = iplAnalyser.getPlayerWithBestStrikeRateWith4sAnd6s(maximumFourHitter,maximumSixHitter);
            Assert.assertThat(bestPlayerWithStrikeRate.player, CoreMatchers.is("Andre Russell"));
        } catch (IPLAnalyserException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
}