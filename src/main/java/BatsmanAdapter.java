import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws AnalyserException {
        try {
            Map<String, IPLDAO> Map = super.loadIPLData(IPLMostRunsCSV.class, csvFilePath[0]);


            if (csvFilePath.length == 2)
                this.loadBowlerData(Map, csvFilePath[1]);
            return Map;
        } catch (
                ArrayIndexOutOfBoundsException e) {
            throw new AnalyserException(e.getMessage(), AnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public int loadBowlerData(Map<String, IPLDAO> Map, String csvFilePath) throws AnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowlersCSV> iplIterator = csvBuilder.getCSVFileIterator(reader, IPLBowlersCSV.class);
            Iterable<IPLBowlersCSV> iplBowlersCSVIterable = () -> iplIterator;
            StreamSupport.stream(iplBowlersCSVIterable.spliterator(), false)
                    .filter(csvFilter -> Map.get(csvFilter.playerName) != null)
                    .forEach(iplBowlersCSV -> {
                        Map.get(iplBowlersCSV.playerName).averageOfBowler = iplBowlersCSV.avgOfBowler;
                        Map.get(iplBowlersCSV.playerName).wicketsTaken = iplBowlersCSV.wicketsTaken;
                    });
            return Map.size();
        } catch (IOException | CSVBuilderException e) {
            throw new AnalyserException(e.getMessage(),
                    AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }
}