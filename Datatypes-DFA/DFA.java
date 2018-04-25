import java.util.regex.Pattern;

/**
 * Created by Taimoor AlQuraishi on 3/7/2018.
 */
public class DFA {
    int initialState;
    int noOfStates;
    int[] finalState;
    int[][] transitionTable;
    String[] inputChars;

    public DFA(int initialState, int[] finalState, int[][] transitionTable, int noOfStates, String[] inputChars) {
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
            if(Pattern.compile(inputChars[i]).matcher(ch+"").matches())
                return this.transitionTable[currentState][i];
        }
        return noOfStates;
    }
}
