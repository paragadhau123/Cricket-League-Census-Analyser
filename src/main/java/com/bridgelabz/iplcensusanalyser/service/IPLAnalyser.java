package com.bridgelabz.iplcensusanalyser.service;

import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;

import java.util.Map;

public class IPLAnalyser {
    Map<String, IPLAnalyserDAO> iplAnalyserMap = null;

    public IPLAnalyser(PlayerType playerType) {
        this.playerType = playerType;
    }

    public enum PlayerType {BATSMAN,BOWLER}
    private final PlayerType playerType;
}
