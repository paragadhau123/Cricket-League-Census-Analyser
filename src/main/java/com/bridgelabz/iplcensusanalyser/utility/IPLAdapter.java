package com.bridgelabz.iplcensusanalyser.utility;

import com.bridgelabz.iplcensusanalyser.exception.IPLAnalyserException;
import com.bridgelabz.iplcensusanalyser.model.IPLAnalyserDAO;

import java.util.Map;

public class IPLAdapter {
    public abstract Map<String, IPLAnalyserDAO> loadIPLData(String... csvFilePath)
            throws IPLAnalyserException;


    public <E> Map<String, IPLAnalyserDAO> loadIPLData(Class<E> iplMostRunsCSVClass, String... csvFilePath) {
        return null;
    }
}
