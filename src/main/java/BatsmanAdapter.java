import java.util.Map;

public class BatsmanAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws AnalyserException {
        try {
            Map<String, IPLDAO> Map = super.loadIPLData(IPLMostRunsCSV.class, csvFilePath[0]);
            if (csvFilePath.length == 2) {
                Map = super.loadIPLData(IPLBowlersCSV.class, csvFilePath[1]);
            }
            return Map;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AnalyserException(e.getMessage(), AnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

}
