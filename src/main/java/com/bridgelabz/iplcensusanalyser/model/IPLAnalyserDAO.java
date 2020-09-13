package com.bridgelabz.iplcensusanalyser.model;

public class IPLAnalyserDAO {
    public int wickets;
    public String strikeRate;
    public int runs;
    public int notOut;
    public int hundreds;
    public String highestScore;
    public int sixes;
    public int fours;
    public int fifties;
    public String average;
    public int innings;
    public int match;
    public String playerName;
    public String overs;
    public String economy;
    public int fourWickets;
    public int fiveWickets;

    public IPLAnalyserDAO(IPLMostRunsCSV iplMostRunsCSV) {
        playerName = iplMostRunsCSV.player;
        match = iplMostRunsCSV.match;
        innings = iplMostRunsCSV.innings;
        average = iplMostRunsCSV.average;
        fifties = iplMostRunsCSV.fifties;
        fours = iplMostRunsCSV.fours;
        sixes = iplMostRunsCSV.sixes;
        highestScore = iplMostRunsCSV.highestScore;
        hundreds = iplMostRunsCSV.hundreds;
        notOut = iplMostRunsCSV.notOut;
        runs = iplMostRunsCSV.runs;
        strikeRate = iplMostRunsCSV.strikeRate;
    }

    public IPLAnalyserDAO(IPLMostWicketsCSV iplMostWicketsCSV) {
        playerName = iplMostWicketsCSV.player;
        match = iplMostWicketsCSV.match;
        innings = iplMostWicketsCSV.innings;
        average = iplMostWicketsCSV.average;
        runs = iplMostWicketsCSV.runs;
        strikeRate = iplMostWicketsCSV.strikeRate;
        overs = iplMostWicketsCSV.overs;
        wickets = iplMostWicketsCSV.wickets;
        fourWickets = iplMostWicketsCSV.fourWickets;
        fiveWickets = iplMostWicketsCSV.fiveWickets;
        economy = iplMostWicketsCSV.economy;
    }
}
