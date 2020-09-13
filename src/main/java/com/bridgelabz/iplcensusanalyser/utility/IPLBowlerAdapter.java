package com.bridgelabz.iplcensusanalyser.utility;

import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;
import com.bridgelabz.iplcensusanalyser.model.IPLMostWicketsCSV;

import java.util.Map;

public class IPLBowlerAdapter {
    @Override
    public Map<String, IPLAnalyserDAO> loadIPLData(String... csvFilePath) {
        return super.loadIPLData(IPLMostWicketsCSV.class, csvFilePath);
    }
}
