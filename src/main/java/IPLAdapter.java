import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.model.IPLMostRunsCSV;
import com.bridgelabz.iplcensusanalyser.model.IPLMostWicketsCSV;
import com.bridgelabz.iplcensusanalyser.service.IPLAnalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {
    public abstract Map<String, IPLAnalyserDAO> loadIPLData(String csvFilePath)
            throws IPLAnalyserException;


    public <E> Map<String, IPLAnalyserDAO> loadIPLData(Class<E> IPLCSVClass, String csvFilePath) throws IPLAnalyserException {
        Map<String, IPLAnalyserDAO> iplMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<E> iplIterator = CSVBuilderFactory.createCSVBuilder().getCSVFileIterator(reader, IPLCSVClass);
            Iterable<E> iplCSV = () -> iplIterator;
            IPLAnalyser.PlayerType playerType = IPLAnalyser.playerType;
            switch (playerType) {
                case BATSMAN:
                    StreamSupport.stream(iplCSV.spliterator(), false)
                            .map(IPLMostRunsCSV.class::cast)
                            .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLAnalyserDAO(iplCSVObj)));
                    break;
                case BOWLER:
                    StreamSupport.stream(iplCSV.spliterator(), false)
                            .map(IPLMostWicketsCSV.class::cast)
                            .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLAnalyserDAO(iplCSVObj)));
                    break;
            }
            return iplMap;
        } catch (IOException e) {
            throw new IPLAnalyserException(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new IPLAnalyserException(e.type.name(), e.getMessage());
        }
    }
}
