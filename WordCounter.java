import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    public String processText(StringBuffer text, String stopWord) {
        Pattern regex = Pattern.compile("your regular expression here");
        Matcher regexMatcher = regex.matcher(text);

        while (regexMatcher.find()) {
            System.out.println("I just found the word: " + regexMatcher.group());
        } 

        return "" ;
    }

    public static StringBuffer processFile(String s) {

        return new StringBuffer() ;
    }

    public static void main(String stopWord) {
        System.out.println("Enter 1 or 2.\n");
        Scanner scanner = new Scanner(System.in) ;

        int choice = scanner.nextInt(); 

        while(choice != 1 && choice != 2) {
            System.out.println("Invalid input. Please enter 1 or 2.\n") ;
            choice = scanner.nextInt();
        }

        scanner.close();

        if(choice == 1) {
            try {
                processFile() ;                
            } catch (TooSmallTextException e) {
               
            } catch (InvalidStopwordException e) {
                
            }
        } else if(choice == 2) {
            try {
                processText() ;              
            } catch (EmptyFileException e) {

            } catch (InvalidStopwordException e) {

            } catch (TooSmallTextException e) {

            }
        }
    }
}
