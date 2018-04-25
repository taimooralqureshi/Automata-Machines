
import java.util.ArrayList;
import java.util.Stack;


public class NFA {
    private int noOfStates;
    private int initialState;
    private int currentState;
    private int index;
    private String input;
    int transitionIndex;
    private ArrayList<Integer> finalStates;
    private ArrayList<Character> inputChar;
    private ArrayList<ArrayList<transition>> transitionTable ;
    private Stack<Integer> stateStack;
    private Stack<Integer> transitionIndexStack;





    public NFA(int noOfStates, int initialState, ArrayList<Integer> finalStates, ArrayList<ArrayList<transition>> transitionTable, ArrayList<Character> inputChar) {
        this.noOfStates = noOfStates;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitionTable = transitionTable;
        this.inputChar = inputChar;
        this.currentState = initialState;
        this.index = 0;
        this.transitionIndexStack = new Stack<Integer>();
        this.stateStack = new Stack<Integer>();
        this.transitionIndex = 0;
        currentState = initialState;
    }
    public boolean Validate(String input) {
        this.input = input;
        int previousState;

        while (true){

            while (index < input.length())
            {
                previousState = currentState;
                currentState = transition(currentState,transitionIndex);
                if(currentState == noOfStates)
                {
                    if(!backtrack())
                        return false;
                }else {
                    index++;
                    stateStack.push(previousState);
                    transitionIndex = 0;
                }

            }
            if (isFinalState(currentState))
                return true;
            else{
                if(!backtrack())
                    return false;
            }
        }


    }

    private int transition(int currentState,int transitionIndex) {
        for (int i = transitionIndex; i < this.transitionTable.get(currentState).size(); i++) {
            if(!inputChar.contains(input.charAt(index)))
                break;
            if(this.transitionTable.get(currentState).get(i).ch.equals(input.charAt(index)+""))
            {
                transitionIndexStack.push(i + 1);
                return transitionTable.get(currentState).get(i).state;
            }
        }
        return noOfStates;
    }

    private boolean backtrack(){
        index--;
        if(stateStack.isEmpty())
            return false;
        currentState = stateStack.pop();
        transitionIndex = transitionIndexStack.pop();
        return true;
    }

    private boolean isFinalState(int state){
        return this.finalStates.stream().mapToInt(fs -> fs).anyMatch(fs -> (int) fs == state);
    }

}
