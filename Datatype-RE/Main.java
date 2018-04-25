import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);

        String IDENTIFIER = "[A-Za-z][A-Za-z0-9]*|_[A-Za-z0-9]+";
        //------------------------------------------------------------------------

        //  \d equivalent to [0-9]
        String INT = "[+-]?\\d+";

        //------------------------------------------------------------------------

        String FLOAT = "[+-]?\\d*\\.\\d+([Ee][+-]?\\d+)?";

        //------------------------------------------------------------------------

//        String CHAR = "^'([^'\\\\]|\\\\[rbtn0'\\\\])'$";
        String CHAR = "^'([^'\\\\])'$";

        //-------------------------------------------------------------------------

        String STRING ="\"([^\\\\\"]|\\\\[rbtn0\"\\\\])*\"";
        //------------------------------------------------------------------------


        while (true) {
            System.out.println("Press Q to Quit");
            System.out.println("input >>>");
            String input = scan.nextLine();
            if(input.toUpperCase().equals("Q"))
                break;
            if(Pattern.compile(IDENTIFIER).matcher(input).matches()) {
                System.out.println("input : identifier");
            } else if(Pattern.compile(INT).matcher(input).matches()) {
                System.out.println("input : integer");
            }
            else if(Pattern.compile(FLOAT).matcher(input).matches()) {
                System.out.println("input : float");
            }
            else if(Pattern.compile(CHAR).matcher(input).matches()) {
                System.out.println("input : Char");
            }
            else if(Pattern.compile(STRING).matcher(input).matches()) {
                System.out.println("input : string");
            }
            else {
                System.out.println("input : invalid");
            }


        }
    }
}
