public class InvalidStopwordException extends Exception {

    public InvalidStopwordException(String s) {
        super("Couldn't find stopword: " + s) ;
    }
    
}
