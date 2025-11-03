public class TooSmallTextException extends Exception {
    public TooSmallTextException(int s) {
        super("Only found " + s + " words.") ;
    }
}


