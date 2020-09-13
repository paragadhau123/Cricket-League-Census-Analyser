import java.util.Map;

public class BowlingAdapter extends IPLAdapter {

    private IPLAdapter adapter;

    @Override
    public Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws AnalyserException {
        Map<String, IPLDAO> map = super.loadIPLData(IPLBowlersCSV.class, csvFilePath[0]);
        return map;
    }
}
