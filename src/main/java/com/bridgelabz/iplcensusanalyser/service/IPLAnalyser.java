package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.utility.IPLAdapterFactory;

import java.util.Map;

public class IPLAnalyser {
    private final PlayerType playerType;
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;

    public IPLAnalyser(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int loadIPLData(PlayerType playerType, String csvFilePath) throws IPLAnalyserException {
        iplAnalyserMap = IPLAdapterFactory.getIPLDataObject(playerType, csvFilePath);
        return iplAnalyserMap.size();
    }

    public enum PlayerType {BATSMAN, BOWLER, ALL_ROUNDER}

}
