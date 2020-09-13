package com.bridgelabz.iplcensusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public double  highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public double  average;

    @CsvBindByName(column = "SR", required = true)
    public double  strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifties;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    @Override
    public String toString() {
        return "IPLMostRunsCSV{" +
                "player='" + player + '\'' +
                ", match=" + match +
                ", innings=" + innings +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highestScore=" + highestScore +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                ", hundreds=" + hundreds +
                ", fifties=" + fifties +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }

}

