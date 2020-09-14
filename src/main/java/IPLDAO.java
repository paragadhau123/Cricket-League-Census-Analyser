public class IPLDAO {

    public String playerName;
    public int matchPlayed;
    public int runsScored;
    public double averageOfBatsmen;
    public double strikeRate;
    public int numberOf4sScored;
    public int numberOf6sScored;
    public double averageOfBowler;
    public double strikeRatesOfBowler;
    public double economyOfBowler;
    public int bowlersWith4Wickets;
    public int bowlersWith5Wickets;
    public int wicketsTaken;

    public IPLDAO(IPLMostRunsCSV iplMostRunsCSV) {
        playerName = iplMostRunsCSV.playerName;
        matchPlayed = iplMostRunsCSV.matchPlayed;
        averageOfBatsmen = iplMostRunsCSV.averageOfBatsmen;
        runsScored = iplMostRunsCSV.runsScored;
        strikeRate = iplMostRunsCSV.strikeRate;
        numberOf4sScored = iplMostRunsCSV.numberOf4sScored;
        numberOf6sScored = iplMostRunsCSV.numberOf6sScored;
    }

    public IPLDAO(IPLBowlersCSV iplBowlersCSV) {
        playerName = iplBowlersCSV.playerName;
        averageOfBowler = iplBowlersCSV.avgOfBowler;
        strikeRatesOfBowler = iplBowlersCSV.strikeRateOfBowler;
        economyOfBowler = iplBowlersCSV.economyOfBowler;
        bowlersWith4Wickets = iplBowlersCSV.wickets4Taken;
        bowlersWith5Wickets = iplBowlersCSV.wickets5Taken;
        wicketsTaken = iplBowlersCSV.wicketsTaken;
    }

}
