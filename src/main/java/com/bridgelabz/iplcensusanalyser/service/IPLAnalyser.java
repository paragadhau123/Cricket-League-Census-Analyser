package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.model.IPLMostRunsCSV;
import com.bridgelabz.iplcensusanalyser.utility.IPLAdapterFactory;
import com.bridgelabz.iplcensusanalyser.utility.IPLUtility;
import com.bridgelabz.iplcensusanalyser.utility.SortField;
import com.google.gson.Gson;

import java.util.*;

public class IPLAnalyser {
    enum PlayerType {BATSMAN, BOWLER}

    public static PlayerType playerType = null;

    List<IPLAnalyserDAO> iplList = null;
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;
    Map<SortField, Comparator<IPLAnalyserDAO>> sortMap = null;

    public IPLAnalyser(PlayerType playerType) {
        IPLAnalyser.playerType = playerType;
    }

    public IPLAnalyser() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVERAGE, Comparator.comparing(ipl -> ipl.average));
        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(ipl -> ipl.strikeRate));
        this.sortMap.put(SortField.SIXES_AND_FOURS, Comparator.comparing(ipl -> ipl.sixes + ipl.fours));
        Comparator<IPLAnalyserDAO> foursAndSixesComparator = Comparator.comparing(ipl -> ipl.sixes + ipl.fours);
        this.sortMap.put(SortField.FOURS_AND_SIXES_WITH_STRIKERATE, foursAndSixesComparator.thenComparing(ipl -> ipl.strikeRate));
    }

    public int loadIPLData(PlayerType playerType, String csvFilePath) throws IPLAnalyserException {
        iplAnalyserMap = IPLAdapterFactory.getIPLDataObject(playerType, csvFilePath);
        return iplAnalyserMap.size();
    }

    public String getSortedData(SortField field) throws IPLAnalyserException {
        if (iplList == null || iplList.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        iplList = new ArrayList<>(iplAnalyserMap.values());
        this.sort(iplList, this.sortMap.get(field).reversed());
        return new Gson().toJson(iplList);

    }

    private void sort(List<IPLAnalyserDAO> iplList, Comparator<IPLAnalyserDAO> iplComparator) {
        for (int i = 0; i < iplList.size() - 1; i++) {
            for (int j = 0; j < iplList.size() - i - 1; j++) {
                IPLAnalyserDAO ipl1 = iplList.get(j);
                IPLAnalyserDAO ipl2 = iplList.get(j + 1);
                if (iplComparator.compare(ipl1, ipl2) > 0) {
                    iplList.set(j, ipl2);
                    iplList.set(j + 1, ipl1);
                }
            }
        }
    }

    public IPLMostRunsCSV getMaximumFourHitter() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.fours);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getMaximumSixHitter() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.sixes);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getTopStrikeRatePlayer() throws IPLAnalyserException {
        if (iplAnalyserMap == null || iplAnalyserMap.size() == 0) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.NO_DATA, "No Data");
        }
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostFoursCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        return iplMostFoursCSV[0];
    }

    public IPLMostRunsCSV getPlayerWithBestStrikeRateWith4sAnd6s(IPLMostRunsCSV maximumFourHitter, IPLMostRunsCSV maximumSixHitter) {
        IPLMostRunsCSV bestPlayer = maximumSixHitter;
        if (maximumFourHitter.average > maximumSixHitter.average) {
            bestPlayer = maximumFourHitter;
        }
        return bestPlayer;
    }

    public IPLMostRunsCSV getPlayerWithGreatAverageAndBestStrikeRate() {
        Comparator<IPLAnalyserDAO> iplComparator = Comparator.comparing(iplData -> iplData.average);
        ArrayList iplDTO = IPLUtility.sort(iplComparator, iplAnalyserMap, true);
        String sortedData = new Gson().toJson(iplDTO);
        IPLMostRunsCSV[] iplMostRunsCSV = new Gson().fromJson(sortedData, IPLMostRunsCSV[].class);
        IPLMostRunsCSV bestPlayer = iplMostRunsCSV[0];
        for (IPLMostRunsCSV mostRunsCSV : iplMostRunsCSV) {
            if (iplMostRunsCSV[0].strikeRate < mostRunsCSV.strikeRate && iplMostRunsCSV[0].average < mostRunsCSV.average) {
                bestPlayer = mostRunsCSV;
                break;
            }
        }
        return bestPlayer;
    }
}



