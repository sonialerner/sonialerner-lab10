import java.io.IOException;

public class EmptyFileException extends IOException {

    public EmptyFileException(String s) {
        super(s + " was empty") ;
    }
    
}
