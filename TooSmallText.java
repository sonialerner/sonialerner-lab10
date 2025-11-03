public class TooSmallText extends Exception {
    public TooSmallText(int s) {
        super("Only found " + s + " words.") ;
    }
}


