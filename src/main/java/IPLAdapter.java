import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    public abstract Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws AnalyserException;

    protected <T> Map<String, IPLDAO> loadIPLData(Class<T> csvClass, String filePath) throws AnalyserException {
        Map<String, IPLDAO> iplHashMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<T> iplCSVIterator = csvBuilder.getCSVFileIterator(reader, csvClass);
            Iterable<T> iplDAOIterable = () -> iplCSVIterator;
            if (csvClass.getName().equals("IPLMostRunsCSV")) {
                StreamSupport.stream(iplDAOIterable.spliterator(), false)
                        .map(IPLMostRunsCSV.class::cast)
                        .forEach(iplCSV -> iplHashMap.put(iplCSV.playerName, new IPLDAO(iplCSV)));
            } else if (csvClass.getName().equals("IPLBowlersCSV")) {
                StreamSupport.stream(iplDAOIterable.spliterator(), false)
                        .map(IPLBowlersCSV.class::cast)
                        .forEach(iplBowlersCSV -> iplHashMap.put(iplBowlersCSV.playerName, new IPLDAO(iplBowlersCSV)));
            }
            return iplHashMap;
        } catch (NoSuchFileException e) {
            throw new AnalyserException(e.getMessage(), AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException | IOException e) {
            throw new AnalyserException(e.getMessage(), AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new AnalyserException(e.getMessage(), AnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
