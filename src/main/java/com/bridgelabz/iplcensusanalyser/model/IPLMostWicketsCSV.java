package com.bridgelabz.iplcensusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public String overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "Econ", required = true)
    public String economy;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @Override
    public String toString() {
        return "IPLMostWicketsCSV{" +
                "player='" + player + '\'' +
                ", match=" + match +
                ", innings=" + innings +
                ", overs=" + overs +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", average=" + average +
                ", economy=" + economy +
                ", strikeRate=" + strikeRate +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }
}
