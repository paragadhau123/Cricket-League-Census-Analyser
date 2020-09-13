package com.bridgelabz.iplcensusanalyser.utility;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.service.IPLAnalyser;

import java.util.Map;

public class IPLAdapterFactory {
    public static Map<String, IPLAnalyserDAO> getIPLDataObject(IPLAnalyser.PlayerType playerType,
                                                               String... csvFilePath) throws IPLAnalyserException {
        if (playerType.equals(IPLAnalyser.PlayerType.BATSMAN))
            return new IPLBatsmanAdapter().loadIPLData(csvFilePath);
        return new IPLBowlerAdapter().loadIPLData(csvFilePath);
    }
}

