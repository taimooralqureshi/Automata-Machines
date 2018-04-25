import java.util.regex.Pattern;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here

        //============================================================================
        //       IDENTIFIER
        //============================================================================

        int[][] identifer_tt = {{1, 2, 3}, {3, 2, 2}, {3, 2, 2}, {3, 3, 3}};
        DFA identifer_DFA = new DFA(0, new int[]{2}, identifer_tt, 4, new String[]{"_", "[A-Za-z]", "[0-9]"});

        System.out.println(identifer_DFA.validate("_456"));

        //============================================================================
        //       INTEGER
        //============================================================================

        int[][] integer_tt = {{1, 2}, {3, 2}, {3, 2}, {3, 3}};
        DFA integer_DFA = new DFA(0, new int[]{2}, integer_tt, 4, new String[]{"[+-]", "[0-9]"});
        System.out.println(integer_DFA.validate("45m6"));


        //============================================================================
        //       FLOAT
        //============================================================================

        int[][] float_tt = {{1, 2, 1, 7}, {7, 2, 1, 7}, {7, 7, 3, 7}, {7, 7, 3, 4}, {5, 7, 6, 7}, {7, 7, 6, 7}, {7, 7, 6, 7}, {7, 7, 7, 7}};
        DFA float_DFA = new DFA(0, new int[]{3, 6}, float_tt, 8, new String[]{"[+-]", "\\.", "[0-9]", "[Ee]"});
        System.out.println(float_DFA.validate("+56.0e-3") + "f");

             //============================================================================
        //       CHAR
        //============================================================================



        String A1 = "^([^'\\\\btnr0])$"; // with slash
        String B1 = "[rbtn0]"; //with & without slash
        char ch = '\\';
        int[][] char_tt = {{1, 5, 5, 5}, {5, 3, 3, 2}, {3, 5, 3, 3}, {4, 5, 5, 5}, {5, 5, 5, 5}, {5, 5, 5, 5}};
        DFA char_DFA = new DFA(0, new int[]{4}, char_tt, 6, new String[]{"'", A1, B1, "\\\\"});
        System.out.println("----char----");
        System.out.println("'\r'");
        System.out.println(char_DFA.validate("'\r'") +"'\\r'");
        System.out.println(char_DFA.validate("'r'") + "'r'");
        System.out.println(char_DFA.validate("'\\'") + "'\\\\'");
        System.out.println(char_DFA.validate("'\''") + "'\\''");
        System.out.println(char_DFA.validate("'''") + "'''");
        System.out.println(char_DFA.validate("'\"'") + "'\"'");

        //============================================================================
        //       STRING
        //============================================================================

        String A2 = "^([^\"\\\\btnr0])$"; // with slash
        String B2 = "[rbtn0]"; //with & without slash
        int[][] string_tt = {{1, 4, 4, 4}, {2, 1, 1, 3}, {4, 4, 4, 4}, {1, 4, 1, 1}, {4, 4, 4, 4}};
        DFA string_DFA = new DFA(0, new int[]{2}, string_tt, 6, new String[]{"\"",A2 ,B2 , "\\\\"});
        System.out.println("--------");
        System.out.println(string_DFA.validate("\"aac234we\""));

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Press Q to Quit");
            System.out.println("input >>>");
            String input = scan.nextLine();
            if (input.toUpperCase().equals("Q"))
                break;
            if (identifer_DFA.validate(input)) {
                System.out.println("input : identifier");
            } else if (integer_DFA.validate(input)) {
                System.out.println("input : integer");
            } else if (float_DFA.validate(input)) {
                System.out.println("input : float");
            } else if (char_DFA.validate(input)) {
                System.out.println("input : Char");
            } else if (string_DFA.validate(input)) {
                System.out.println("input : string");
            } else {
                System.out.println("input : invalid");
            }

        }


    }}
