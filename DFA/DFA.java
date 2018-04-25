import java.util.ArrayList;

/**
 * Created by Taimoor AlQuraishi on 3/7/2018.
 */
public class DFA implements Cloneable{
    public int initialState;
    public int noOfStates;
    public ArrayList<Integer> finalState;
    public ArrayList<ArrayList<Integer>> transitionTable;
    public char[] inputChars;

    public DFA(int initialState, ArrayList<Integer> finalState, ArrayList<ArrayList<Integer>> transitionTable, int noOfStates, char[] inputChars) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.transitionTable = transitionTable;
        this.noOfStates = noOfStates;
        this.inputChars = inputChars;
    }
    public boolean validate(String input)
    {
        int currentState = this.initialState;
        for (char ch: input.toCharArray()) {
            currentState = transition(currentState, ch);
            if(noOfStates <= currentState)
                return false;
        }
        for (int fs: finalState) {
            if(fs == currentState)
                return true;
        }
        return false;
    }
    public int transition(int currentState, char ch){
        for (int i = 0; i <inputChars.length ; i++) {
            if(ch == inputChars[i])
                return this.transitionTable.get(currentState).get(i);
        }
        return noOfStates;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public int isFinalState(int st)
    {
        for (int state :
                this.finalState) {
            if(st == state)
            {
                return state;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String finalState = "[ ";
        for (int s :
                this.finalState) {
            finalState += s+" ";
        }
//        System.out.println("IS: " + this.initialState + " FS: " + finalState+"]");
        System.out.println("--------------------");
        System.out.println("     | "+ inputChars[0] +"  |   | "+inputChars[1]+"  |\n--------------------");
        for (int i = 0; i < this.transitionTable.size(); i++) {
            String transition = "Z"+i;
            for (int j = 0; j < this.transitionTable.get(i).size(); j++) {
                transition += "   | Z"+this.transitionTable.get(i).get(j)+" |";
            }
            if(isFinalState(i)!= -1)
                transition += " "+"+";
            if(initialState == i)
                transition += " "+"-";


            System.out.println(transition);
        }
        System.out.println("--------------------");
        return "";
    }
}
