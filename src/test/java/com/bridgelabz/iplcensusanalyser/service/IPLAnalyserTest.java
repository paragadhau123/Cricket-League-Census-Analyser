package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import junit.framework.TestCase;
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
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100,count);
        } catch (IPLAnalyserException e){
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturn_CorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BOWLER);
            int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER,MOST_WICKETS_CSV_FILE_PATH);
            Assert.assertEquals(98,count);
        } catch (IPLAnalyserException e){
            System.out.println("Fail");
            e.printStackTrace();
        }
    }
}