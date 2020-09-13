package com.bridgelabz.iplcensusanalyser.exception;

public class IPLAnalyserException extends Exception {
    public IPLAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public IPLAnalyserException(String name, String message) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public enum ExceptionType {NO_DATA, FILE_NOT_FOUND, PARSE_ERROR, IPL_FILE_PROBLEM}

    public ExceptionType type;
}
