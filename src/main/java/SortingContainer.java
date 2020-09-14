import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingContainer {

    static Map<FieldsToSort, Comparator> iplHashMap = new HashMap<>();

    public Comparator getParameter(FieldsToSort parameter) {

        Comparator<IPLDAO> battingAverageComparator = Comparator.comparing(compare -> compare.averageOfBatsmen);

        Comparator<IPLDAO> battingStrikeRateComparator = Comparator.comparing(compare -> compare.strikeRate);

        Comparator<IPLDAO> bowlingAverageComparator = Comparator.comparing(compare -> compare.averageOfBowler);

        Comparator<IPLDAO> bowlingStrikeRateComparator = Comparator.comparing(compare -> compare.strikeRatesOfBowler);

        Comparator<IPLDAO> runsScoredComparator = Comparator.comparing(compare -> compare.runsScored);

        Comparator<IPLDAO> wicketsTakenComparator = Comparator.comparing(compare -> compare.wicketsTaken);

        Comparator<IPLDAO> bowlingEconomyRateComparator = Comparator.comparing(compare -> compare.economyOfBowler);

        Comparator<IPLDAO> mostRunsAndMostWicketsComparator = Comparator.comparing(compare ->
                (compare.runsScored )+ (compare.wicketsTaken));

        Comparator<IPLDAO> most4WsAnd5WsComparator = Comparator.comparing(compare -> (compare.
                bowlersWith4Wickets) + (compare.bowlersWith5Wickets));

        Comparator<IPLDAO> mostBattingAndMostBowlingAverageComparator = Comparator.comparing(compare ->
                (compare.averageOfBatsmen) + (compare.averageOfBowler));

        Comparator<IPLDAO> most4sAnd6sComparator = Comparator.comparing(compare -> (compare.
                numberOf6sScored) + (compare.numberOf4sScored));

        iplHashMap.put(FieldsToSort.BY_AVERAGE, battingAverageComparator);

        iplHashMap.put(FieldsToSort.BY_STRIKERATE, battingStrikeRateComparator);

        iplHashMap.put(FieldsToSort.BY_4s_AND_6s, most4sAnd6sComparator.reversed());

        iplHashMap.put(FieldsToSort.BY_STRIKERATE_WITHMOST_4sAND6s, most4sAnd6sComparator
                .reversed().thenComparing(compare -> compare.strikeRate));

        iplHashMap.put(FieldsToSort.BY_AVERAGE_WITH_STRIKE_RATE, battingAverageComparator.
                thenComparing(compare -> compare.strikeRate).reversed());

        iplHashMap.put(FieldsToSort.BY_AVERAGE_WITH_MOST_RUNS, runsScoredComparator.thenComparing
                (compare -> compare.averageOfBatsmen).reversed());

        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_AVERAGES, bowlingAverageComparator);

        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_STRIKING_RATES, bowlingStrikeRateComparator);

        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_ECONOMY_RATES, bowlingEconomyRateComparator);

        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_STRIKING_RATES_WITH_MOST_4Ws_AND_5Ws,
                most4WsAnd5WsComparator.reversed().thenComparing(compare -> compare.strikeRatesOfBowler));

        iplHashMap.put(FieldsToSort.BY_GREAT_AVERAGE_RATE_WITH_BEST_STRIKING_RATE, battingAverageComparator.
                thenComparing(compare -> compare.strikeRatesOfBowler));

        iplHashMap.put(FieldsToSort.BY_MOST_WICKETS_WITH_GREAT_AVERAGE_RATE, wicketsTakenComparator.
                thenComparing(compare -> compare.averageOfBatsmen));

        iplHashMap.put(FieldsToSort.BY_MOST_BATTING_AVERAGE_WITH_MOST_BOWLING_AVERAGE,
                mostBattingAndMostBowlingAverageComparator);

        iplHashMap.put(FieldsToSort.BY_MOST_BATTING_RUNS_WITH_MOST_BOWLING_WICKETS,
                mostRunsAndMostWicketsComparator);

        Comparator<IPLDAO> comparatorToSort = iplHashMap.get(parameter);

        return comparatorToSort;
    }

    public Comparator<IPLDAO> getData(FieldsToSort fields) {

        return getParameter(fields);
    }
}
