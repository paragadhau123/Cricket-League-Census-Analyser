public class AnalyserException extends Exception {

    enum ExceptionType {
        NO_SUCH_FILE,CENSUS_FILE_PROBLEM,NO_CENSUS_DATA
    }

    ExceptionType type;

    public AnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
