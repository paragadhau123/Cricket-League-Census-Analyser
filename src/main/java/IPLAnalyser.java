import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {
    Map<String, IPLDAO> iplHashMap;
    private PlayerStats statistics;
    SortingContainer container;
    public IPLAdapter adapter;

    public IPLAnalyser(PlayerStats statistics) {
        container = new SortingContainer();
        this.statistics = statistics;
    }

    public void setAdapter(IPLAdapter adapter ) {
        this.adapter = adapter;
    }

    public Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws AnalyserException {
        iplHashMap = this.adapter.loadIPLData(csvFilePath);
        return iplHashMap;
    }

    public String getFieldWiseSortedIPLData(FieldsToSort fieldName, Map<String, IPLDAO> iplHashMap) throws AnalyserException {
        Comparator<IPLDAO> comparator = null;
        SortingContainer container = new SortingContainer();
        if (iplHashMap == null || iplHashMap.size() == 0) {
            throw new AnalyserException("NO_CENSUS_DATA", AnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        comparator = container.getData(fieldName);
        ArrayList arrayList = iplHashMap.values().stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(arrayList);
        return sortedStateCensus;
    }
}
