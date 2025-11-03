import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    public static int processText(StringBuffer text, String stopWord) throws InvalidStopwordException, TooSmallTextException {
        int numWords = 0;
        Pattern regex = Pattern.compile("[a-zA-Z'0-9]");
        Matcher regexMatcher = regex.matcher(text);

        Pattern inputRegex = Pattern.compile(stopWord) ;
        Matcher inputMatcher = inputRegex.matcher(stopWord) ;

        if(!inputMatcher.find())
            throw new InvalidStopwordException(stopWord) ;

        while (regexMatcher.find()) {
            numWords++;
            // System.out.println("I just found the word: " + regexMatcher.group());
        } 

        if(numWords < 5)
            throw new TooSmallTextException(stopWord) ;

        return numWords ;
    }

    public static StringBuffer processFile(String s) throws EmptyFileException, FileNotFoundException {
        String filePath = s ;

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Scanner inputScanner = new Scanner(new InputStreamReader(inputStream)) ;
            StringBuffer result = new StringBuffer() ;

            if(!inputScanner.hasNext()) {
                inputScanner.close() ;
                throw new EmptyFileException(s) ;
            }

            while(inputScanner.next() != null)
                result.append(inputScanner.next()) ;      
                
            inputScanner.close() ;
            return result ;
        } catch(FileNotFoundException e) {
            System.out.println("Please enter a valid file path.\n");
            Scanner scanner = new Scanner(System.in) ;
            filePath = scanner.next() ;
            scanner.close() ;
        } catch (EmptyFileException e) {
            System.out.println(e) ;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        return null; 
    }

    public static void main(String[] args) throws EmptyFileException, FileNotFoundException {
        System.out.println("Enter 1 or 2.\n");
        Scanner scanner = new Scanner(System.in) ;

        int choice = scanner.nextInt(); 

        while(choice != 1 && choice != 2) {
            System.out.println("Invalid input. Please enter 1 or 2.\n") ;
            choice = scanner.nextInt();
        }

        scanner.close();

        StringBuffer buffer ;

        // create new StringBuffer
        if(choice == 1)
            buffer = new StringBuffer(processFile(args[0])) ;  
        else
            buffer = new StringBuffer(args[0]) ;

        try {
            int result = processText(buffer, args[1]) ;
            System.out.println(result) ;
        } catch (InvalidStopwordException e) {
            System.out.println(e) ;
        } catch (TooSmallTextException e) {
            System.out.println(e) ;
        } catch (Exception e) {
            System.out.println(e) ;
        }
    }
}
