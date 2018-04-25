
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ArrayList<ArrayList<transition>> tt = new ArrayList<ArrayList<transition>>() {{
            add(new ArrayList<transition>() {{
                add(new transition(0, "a"));
                add(new transition(1, "a"));
                add(new transition(4, "a"));
            }});
            add(new ArrayList<transition>() {{
                add(new transition(2, "a"));
            }});
            add(new ArrayList<transition>() {{
                add(new transition(3, "b"));
            }});


            add(new ArrayList<transition>() {{

            }});

            add(new ArrayList<transition>() {{
                add(new transition(4, "b"));
                add(new transition(5, "b"));
            }});

            add(new ArrayList<transition>() {{
                add(new transition(3, "b"));
            }});
        }};

        NFA nfa = new NFA(6,0, new ArrayList<Integer>(){{add(3);}},tt, new ArrayList<Character>(){{add('a');add('b');}});
        System.out.println(nfa.Validate("aaabb"));
        System.out.println();
    }
}
