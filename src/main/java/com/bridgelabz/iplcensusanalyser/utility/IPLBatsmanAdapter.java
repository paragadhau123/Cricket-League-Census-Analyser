package com.bridgelabz.iplcensusanalyser.utility;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.model.IPLMostRunsCSV;

import java.util.Map;

public class IPLBatsmanAdapter {
    @Override
    public Map<String, IPLAnalyserDAO> loadIPLData(String... csvFilePath) throws IPLAnalyserException {
        return super.loadIPLData(IPLMostRunsCSV.class,csvFilePath);
    }
}
}
