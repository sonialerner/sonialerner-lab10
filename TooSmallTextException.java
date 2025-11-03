public class TooSmallTextException extends Exception {
    public TooSmallTextException(int s) {
        super("Only found " + s + " words.") ;
    }

    @Override
    public String toString() {
        return "TooSmallText: " + getMessage() ;
    }
}




