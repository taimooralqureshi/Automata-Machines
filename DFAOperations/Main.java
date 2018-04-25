import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
	// write your code here

//DFA GENERAL TEST
//        ArrayList<ArrayList<Integer>> tt = new ArrayList<ArrayList<Integer>>(){{
//            add(new ArrayList<>(){{
//                add(1);
//                add(3);
//            }});
//            add(new ArrayList<>(){{
//                add(2);
//                add(3);
//            }});
//            add(new ArrayList<>(){{
//                add(2);
//                add(3);
//            }});
//            add(new ArrayList<>(){{
//                add(1);
//                add(4);
//            }});
//            add(new ArrayList<>(){{
//                add(1);
//                add(4);
//            }});
//
//        }};
//        DFA DFA = new DFA(0,new ArrayList<Integer>(){{add(2);add(4);}},tt, 5, new char[]{'a','b'});
//        System.out.println(DFA.validate("aababababababababaa"));
        DFAOperators dfaOpr = new DFAOperators();


//=======================================================================================================================
//====================================================DFA Intersection===================================================
//=======================================================================================================================

        ArrayList<ArrayList<Integer>> tt1 = new ArrayList<ArrayList<Integer>>(){{
            add(new ArrayList<>(){{
                add(1);
                add(0);
            }});
            add(new ArrayList<>(){{
                add(1);
                add(0);
            }});
        }};

        DFA DFA1 = new DFA(0,new ArrayList<Integer>(){{add(1);}},tt1, 2, new char[]{'a','b'});

        ArrayList<ArrayList<Integer>> tt2 = new ArrayList<ArrayList<Integer>>(){{
            add(new ArrayList<>(){{
                add(1);
                add(0);
            }});
            add(new ArrayList<>(){{
                add(2);
                add(0);
            }});
            add(new ArrayList<>(){{
                add(2);
                add(2);
            }});
        }};

        DFA DFA2 = new DFA(0,new ArrayList<Integer>(){{add(2);}},tt2, 3, new char[]{'a','b'});

        DFA intersection = dfaOpr.dfaIntersection(DFA1,DFA2);
        System.out.println("INTERSECTION");
        System.out.println(intersection);


//=======================================================================================================================
//====================================================DFA Concatination====================================================
//=======================================================================================================================


        ArrayList<ArrayList<Integer>> tt3 = new ArrayList<ArrayList<Integer>>(){{
            add(new ArrayList<>(){{
                add(1);
                add(0);
            }});
            add(new ArrayList<>(){{
                add(1);
                add(0);
            }});
        }};

        DFA DFA3 = new DFA(0,new ArrayList<Integer>(){{add(1);}},tt3, 2, new char[]{'a','b'});

        ArrayList<ArrayList<Integer>> tt4 = new ArrayList<ArrayList<Integer>>(){{
            add(new ArrayList<>(){{
                add(1);
                add(2);
            }});
            add(new ArrayList<>(){{
                add(3);
                add(2);
            }});
            add(new ArrayList<>(){{
                add(1);
                add(3);
            }});
            add(new ArrayList<>(){{
                add(3);
                add(3);
            }});
        }};

        DFA DFA4 = new DFA(0,new ArrayList<Integer>(){{add(3);}},tt4, 4, new char[]{'a','b'});
        System.out.println("CONCATINATION");
        System.out.println(dfaOpr.dfaConcat(DFA3,DFA4));
//=======================================================================================================================
//====================================================DFA Closure========================================================
//=======================================================================================================================
        ArrayList<ArrayList<Integer>> tt5 = new ArrayList<ArrayList<Integer>>(){{
            add(new ArrayList<Integer>(){{
                add(0);
                add(1);
            }});
            add(new ArrayList<Integer>(){{
                add(1);
                add(2);
            }});
            add(new ArrayList<Integer>(){{
                add(2);
                add(3);
            }});
            add(new ArrayList<Integer>(){{
                add(3);
                add(3);
            }});
        }};
        System.out.println("CLOSURE");
        DFA DFA5 = new DFA(0,new ArrayList<Integer>(){{add(2);}},tt5,tt5.size(),new char[]{'a','b'});
        System.out.println(dfaOpr.dfaClosure(DFA5));
    }
}
