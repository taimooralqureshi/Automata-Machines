
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scan = new Scanner(System.in);


        // GENERAL TEST

        ArrayList<ArrayList<Integer>> tt = new ArrayList<ArrayList<Integer>>(){{
           add(new ArrayList<Integer>(){{
               add(1);
               add(3);
           }}) ;
           add(new ArrayList<Integer>(){{
               add(2);
               add(3);
           }});
            add(new ArrayList<Integer>(){{
               add(2);
               add(3);
           }});
           add(new ArrayList<Integer>(){{
               add(1);
               add(4);
           }});
           add(new ArrayList<Integer>(){{
               add(1);
               add(4);
           }});
        }};
        DFA DFA = new DFA(0,new ArrayList<Integer>(){{add(2);add(4);}},tt, 5, new char[]{'a','b'});
        System.out.println(DFA.validate("aab"));



//        System.out.println("Input allowed no of character>>");
//        int allowedChars = scan.nextInt();
//        System.out.println("Input allowed character>>");
//        char[] inputChar = new char[allowedChars];
//        for (int i = 0; i < inputChar.length; i++) {
//            inputChar[i] = scan.next().charAt(0);
//        }
//
//        System.out.println("Input No. of states>>>");
//        int NOS = scan.nextInt();
//
//        System.out.println("Input initial state");
//        int IS = scan.nextInt();
//        System.out.println("Input No of Final States");
//        int NoOfFS = scan.nextInt();
//        System.out.println("Input Final States");
//        ArrayList<Integer> FS = new ArrayList<Integer>();
//        for (int i = 0; i < NoOfFS; i++) {
//            FS.add(scan.nextInt());
//        }
//        DFA automateDFA = new DFA(0, , tt, NOS, inputChar);
        while(true){

            System.out.println("input word>>>");
            String input = scan.next();
            if(input.charAt(0) == 'q')
                break;
            System.out.println("Result : " + DFA.validate(input) );
            System.out.println("Press Q to Quit");

        }
        System.out.println(DFA);
    }
}
