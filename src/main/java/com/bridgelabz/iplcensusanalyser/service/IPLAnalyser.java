package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.model.IPLMostRunsCSV;
import com.bridgelabz.iplcensusanalyser.utility.IPLAdapterFactory;
import com.bridgelabz.iplcensusanalyser.utility.IPLUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class IPLAnalyser {
    public static PlayerType playerType = null;
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;

    public IPLAnalyser(PlayerType playerType) {
        IPLAnalyser.playerType = playerType;
    }

    public int loadIPLData(PlayerType playerType, String csvFilePath) throws IPLAnalyserException {
        iplAnalyserMap = IPLAdapterFactory.getIPLDataObject(playerType, csvFilePath);
        return iplAnalyserMap.size();
    }
    public IPLMostRunsCSV getTopBattingAveragePlayer() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0){
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA,"No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.average);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostRunsCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostRunsCSV[0];
    }
    public IPLMostRunsCSV getMaximumFourHitter() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0){
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA,"No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.fours);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getMaximumSixHitter() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0){
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA,"No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.sixes);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }
    public IPLMostRunsCSV getTopStrikeRatePlayer() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0){
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA,"No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }
    public enum PlayerType {BATSMAN, BOWLER}

}
