import java.util.ArrayList;


public class TG {

    ArrayList<Integer> initialState;

    ArrayList<Integer> finalState;
    ArrayList<ArrayList<transition>> transitionTable;

    ArrayList<transition> loopTransitions;



    public TG(ArrayList initialState, ArrayList finalState, ArrayList transitionTable) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.transitionTable = transitionTable;

    }

    public String toRE() {
        
        //adding initial state in transition table.
        ArrayList<transition> IS = new ArrayList<>();
        for (int i = 0; i < initialState.size(); i++) {
            IS.add(new transition(initialState.get(i), ""));
        }
        this.transitionTable.add(IS);
        this.initialState.clear();
        this.initialState.add(this.transitionTable.size()-1);
        


        //adding final state in transition table.
        for (Integer aFinalState : finalState)
            this.transitionTable.get(aFinalState).add(new transition(this.transitionTable.size(), ""));
        this.transitionTable.add(new ArrayList<transition>());
        this.finalState.clear();
        this.finalState.add(this.transitionTable.size()-1);


        
        
        // Reducing multiple transition on same state.
        ReduceTransitions();




         //Loop transition handling
        this.loopTransitions = new ArrayList<>() {


            @Override
            public int indexOf(Object o) {
                int state = (int)o;
                for (int i = 0; i < this.size(); i++) {
                    if(this.get(i).state == state)
                        return i;
                }
                return -1;
            }
        };
        loopTransition();




        //States elimination
        statesElimination();

        return this.transitionTable.get(initialState.get(0)).get(0).ch;
    }

    public void ReduceTransitions(){
        ArrayList<transition> arr = new ArrayList<transition>(){
            @Override
            public boolean contains(Object o) {
                transition tr = (transition)o;

                for (int i = 0; i < this.size(); i++) {
                    if(this.get(i).state == tr.state)
                    {
                        transition temp = this.get(i);
                        temp.ch = "(("+temp.ch + ")+(" + tr.ch +"))";
                        return true;
                    }

                }
                return false;
            }
        };

        for (int i = 0; i < this.transitionTable.size(); i++) {
            arr.clear();
            for (int j = 0; j < this.transitionTable.get(i).size(); j++) {
                if(!arr.contains(this.transitionTable.get(i).get(j)))
                {
                    arr.add(this.transitionTable.get(i).get(j));
                }
            }
            this.transitionTable.get(i).clear();
            for (transition tr: arr
                    ) {
                this.transitionTable.get(i).add(tr);
            }

        }

    }


    public void loopTransition(){

        for (int i = 0; i < this.transitionTable.size()-2; i++) {
            for (int j = 0; j < this.transitionTable.get(i).size(); j++) {
                int itt = this.transitionTable.get(i).get(j).state;
                if(i == this.transitionTable.get(i).get(j).state)
                {
                    loopTransitions.add(this.transitionTable.get(i).get(j));
                    this.transitionTable.get(i).remove(j);
                    break;
                }

            }
        }

    }

    public void statesElimination(){
        for (int a = 0; a < this.transitionTable.size()-2; a++) {

            int eliminatingState = a;
            int j = 0;
            for (int b = 0; b < this.transitionTable.get(this.initialState.get(0)).size(); b++) {
                if(this.transitionTable.get(this.initialState.get(0)).get(b).state == eliminatingState)
                {
                    j = b;
                }
            }
            String temp = this.transitionTable.get(this.initialState.get(0)).get(j).ch;

            for (transition tr: this.loopTransitions)
            {
                if(eliminatingState == tr.state)
                    temp += "(" + tr.ch + ")*";

            }
            for (int i = 0; i < this.transitionTable.get(eliminatingState).size(); i++) {
                String str = temp;
                str += this.transitionTable.get(eliminatingState).get(i).ch;
                this.transitionTable.get(this.initialState.get(0)).add(new transition(this.transitionTable.get(eliminatingState).get(i).state,str));
            }
            for (int i = eliminatingState+1; i < this.transitionTable.size()-2; i++) {// itterate over states
                for (int l = 0; l < this.transitionTable.get(i).size(); l++) { // itterate over state transition
                    if(this.transitionTable.get(i).get(l).state == eliminatingState)
                    {
                        String str1 = this.transitionTable.get(i).get(l).ch;
                        for (int m = 0; m < this.transitionTable.get(eliminatingState).size(); m++) { // itterate over transition of eliminating state
                            int state = this.loopTransitions.indexOf(eliminatingState);
                            if(state != -1)
                            {
                                str1 += "(" + this.loopTransitions.get(state).ch + ")*";
                            }
                            String str2 = str1 + this.transitionTable.get(eliminatingState).get(m).ch ;
                            this.transitionTable.get(i).add(new transition(this.transitionTable.get(eliminatingState).get(m).state,str2));
                            this.transitionTable.get(i).remove(this.transitionTable.get(i).get(l));
                        }
                    }
                }
            }

            this.transitionTable.get(initialState.get(0)).remove(j);
            ReduceTransitions();
            loopTransition();
        }


    }
}

