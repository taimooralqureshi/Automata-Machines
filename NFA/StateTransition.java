import java.util.ArrayList;

public class StateTransition {
    ArrayList<Integer>a,b;

    public StateTransition() {
        a = new ArrayList<>();
        b = new ArrayList<>();
    }

    public StateTransition(ArrayList<Integer> a, ArrayList<Integer> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        String str = "{a : ";
        for (int i: a
             ) {
            str += i+" ";
        }
        str += ", b : ";
        for (int i: b
                ) {
            str += i+" ";
        }
        return str += ")";
    }
}
