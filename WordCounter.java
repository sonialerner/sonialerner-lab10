import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    public static int processText(StringBuffer text, String stopWord) throws InvalidStopwordException, TooSmallText {
        
        int numWords = 0;
        boolean found = false;
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);

        if(stopWord == null)
            found = true ;
        
        while (regexMatcher.find()) {
            numWords++;
            if(regexMatcher.group().equals(stopWord) && stopWord != null) {
                found = true ;
                break ;
            }
        } 

        if(numWords < 5)
            throw new TooSmallText(numWords) ;
        
        if(!found)
            throw new InvalidStopwordException(stopWord) ;

        return numWords ;
    }

    public static StringBuffer processFile(String s) throws EmptyFileException {
        boolean valid = false ;
        Scanner scanner = new Scanner(System.in) ;
        StringBuffer result = new StringBuffer() ;
        Scanner inputScanner ;
        
        while(!valid) {
            try {
                File file = new File(s) ; // reference file with path name "s"
                inputScanner = new Scanner(file) ; // try to read from that file
                
                while(inputScanner.hasNext()) {
                    result.append(inputScanner.next()) ;
                    result.append(" ") ; // append space so words are separated, but only if there are more words to parse
                }
                
                inputScanner.close() ;
                valid = true ;
            } catch (FileNotFoundException e) {
                System.out.println("Please enter a valid file path.\n");
                s = scanner.next() ;
            }
        }

        scanner.close() ;

        if(result.length() == 0) {
            throw new EmptyFileException(s) ;
        }

        return result ;
    }

    public static void main(String[] args) {

        String stopword = null ;
        if(args.length > 1)
            stopword = args[1] ;

        Scanner scanner = new Scanner(System.in) ;
        System.out.println("Enter 1 or 2.\n");

        int choice = scanner.nextInt(); 

        while(choice != 1 && choice != 2) {
            System.out.println("Invalid input. Please enter 1 or 2.\n") ;
            choice = scanner.nextInt();
        }

        StringBuffer buffer ;
        // create new StringBuffer
        int result ;
        if(choice == 1) { // If caller selects option 1
            try { // FIRST TRY!
                buffer = new StringBuffer(processFile(args[0])) ;

                    try { // INNER TRY!!
                        result = processText(buffer, stopword) ;
                        System.out.println("Found " + result + " words.") ;
                    } catch (InvalidStopwordException e) { // INNER CATCH!
                        System.out.println(e);
                        System.out.println("Please enter a valid stopword.");
                        Scanner newScanner = new Scanner(System.in) ;

                        try { // TRY IN CATCH
                            result = processText(buffer, newScanner.next()) ;
                            System.out.println("Found " + result + " words.") ;
                        } catch (InvalidStopwordException e1) { // CATCH
                            System.out.println(e1);
                        } catch (TooSmallText e1) { // CATCH
                            System.out.println(e1);
                        }

                        newScanner.close();
                    } catch (TooSmallText e) { // SECOND INNER CATCH
                        System.out.println(e);
                    }

            } catch (EmptyFileException e) { // FIRST CATCH!
                System.out.println(e);
                try {
                    result = processText(new StringBuffer(""), stopword) ; // process with empty string buffer
                } catch (InvalidStopwordException e1) {
                    System.out.println(e1);
                    System.out.println("Please enter a valid stopword.");
                    Scanner newScanner = new Scanner(System.in) ;

                    try {
                        result = processText(new StringBuffer(""), newScanner.next()) ;
                        System.out.println("Found " + result + " words.") ;
                    } catch (InvalidStopwordException e2) {
                        System.out.println(e2);
                    } catch (TooSmallText e2) {
                        System.out.println(e2);
                    }

                    newScanner.close();
                } catch (TooSmallText e1) {
                    System.out.println(e1);
                }
            }
        } else { // if caller selects option 2
            try {
                result = processText(new StringBuffer(args[0]), stopword) ;
                System.out.println("Found " + result + " words.") ;
            } catch (InvalidStopwordException e) {
                System.out.println(e);
                System.out.println("Please enter a valid stopword.");
                Scanner newScanner = new Scanner(System.in) ;
                try {
                    result = processText(new StringBuffer(args[0]), newScanner.next()) ;
                    System.out.println("Found " + result + " words.") ;
                } catch (InvalidStopwordException e1) {
                    System.out.println(e1);
                } catch (TooSmallText e1) {
                    System.out.println(e1);
                }
                newScanner.close() ;
            } catch (TooSmallText e) {
                System.out.println(e);
            }
        }
        scanner.close();
    }
}
