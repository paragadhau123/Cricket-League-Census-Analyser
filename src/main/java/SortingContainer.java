
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingContainer {

    static Map<FieldsToSort, Comparator> iplHashMap = new HashMap<>();

    public SortingContainer() {
    }

    public Comparator getParameter(FieldsToSort parameter) {
        Comparator<IPLDAO> battingAverageComparator = Comparator.comparing(compare -> compare.
                averageOfBatsmen);
        Comparator<IPLDAO> battingStrikeRateComparator = Comparator.comparing(census -> census.
                strikeRate);
        Comparator<IPLDAO> bowlingAverageComparator = Comparator.comparing(census -> census.
                averageOfBatsmen);
        Comparator<IPLDAO> bowlingStrikeRateComparator = Comparator.comparing(census -> census.
                strikeRatesOfBowler);
        Comparator<IPLDAO> comparator1 = Comparator.comparing(compare -> compare.runsScored);
        Comparator<IPLDAO> comparator2 = Comparator.comparing(compare -> compare.averageOfBatsmen);
        Comparator<IPLDAO> comparator3 = Comparator.comparing(compare -> compare.wicketsTaken,
                Comparator.reverseOrder());
        Comparator<IPLDAO> mostRunsAndMostWicketsComparator = Comparator.comparing(compare ->
                (compare.runsScored * compare.wicketsTaken));
        Comparator<IPLDAO> bowlingEconomyRateComparator = Comparator.comparing(census -> census
                .economyOfBowler);
        Comparator<IPLDAO> most4WsAnd5WsComparator = Comparator.comparing((compare -> (compare.
                bowlersWith4Wickets * 4 + compare.bowlersWith5Wickets * 5)));
        Comparator<IPLDAO> mostBattingAndMostBowlingAverageComparator = Comparator.comparing(compare ->
                (compare.averageOfBatsmen - (1d / compare.averageOfBowler)));
        Comparator<IPLDAO> most4sAnd6sComparator = Comparator.comparing(compare -> (compare.
                numberOf6sScored * 6) + (compare.numberOf4sScored * 4));

        iplHashMap.put(FieldsToSort.BY_AVERAGE, battingAverageComparator);
        iplHashMap.put(FieldsToSort.BY_STRIKERATE, battingStrikeRateComparator);
        iplHashMap.put(FieldsToSort.BY_4s_AND_6s, most4sAnd6sComparator.reversed());
        iplHashMap.put(FieldsToSort.BY_STRIKERATE_WITHMOST_4sAND6s, most4sAnd6sComparator
                .reversed().thenComparing(compare -> compare.strikeRate));
        iplHashMap.put(FieldsToSort.BY_AVERAGE_WITH_STRIKE_RATE, battingAverageComparator.
                thenComparing(compare -> compare.strikeRate).reversed());
        iplHashMap.put(FieldsToSort.BY_AVERAGE_WITH_MOST_RUNS, comparator1.thenComparing
                (compare1 -> compare1.averageOfBatsmen).reversed());
        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_AVERAGES, bowlingAverageComparator);
        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_STRIKING_RATES, bowlingStrikeRateComparator);
        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_ECONOMY_RATES, bowlingEconomyRateComparator);
        iplHashMap.put(FieldsToSort.BY_TOP_BOWLING_STRIKING_RATES_WITH_MOST_4Ws_AND_5Ws,
                most4WsAnd5WsComparator.reversed().thenComparing(compare -> compare.strikeRatesOfBowler));
        iplHashMap.put(FieldsToSort.BY_GREAT_AVERAGE_RATE_WITH_BEST_STRIKING_RATE, comparator2.
                thenComparing(compare -> compare.strikeRatesOfBowler));
        iplHashMap.put(FieldsToSort.BY_MOST_WICKETS_WITH_GREAT_AVERAGE_RATE, comparator3.
                thenComparing(compare -> compare.averageOfBatsmen));
        iplHashMap.put(FieldsToSort.BY_MOST_BATTING_AVERAGE_WITH_MOST_BOWLING_AVERAGE,
                mostBattingAndMostBowlingAverageComparator);
        iplHashMap.put(FieldsToSort.BY_MOST_BATTING_RUNS_WITH_MOST_BOWLING_WICKETS,
                mostRunsAndMostWicketsComparator);

        Comparator<IPLDAO> comparatorToSort = iplHashMap.get(parameter);

        return comparatorToSort;
    }

    public Comparator<IPLDAO> getData(FieldsToSort fieldName) {

        return getParameter(fieldName);
    }
}
