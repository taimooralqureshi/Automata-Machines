import java.util.ArrayList;
import java.util.Collections;

public class DFAOperators  {
    DFA dfaOR(DFA dfa1, DFA dfa2) {
        ArrayList<State> Z = new ArrayList<State>(){
            @Override
            public boolean contains(Object o) {
                State obj = (State)o;
                for (State state: this
                     ) {

                    if(obj.x == state.x && obj.y.equals(state.y))
                    {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public int indexOf(Object o) {
                State st = (State)o;
                for (int i = 0; i < this.size(); i++) {
                        if(st.x == this.get(i).x && st.y.equals(this.get(i).y))
                        {
                            return i;
                        }

//                    if(st.x == this.get(i).x && st.y == this.get(i).y )
//                        return i;
                }
                return super.indexOf(o);
            }
        };
        ArrayList<ArrayList<Integer>> tt = new ArrayList<>();
        ArrayList<Integer> FS = new ArrayList<>(){
            @Override
            public boolean contains(Object o) {
                int obj = (int)o;
                for (int st :
                        this) {
                    if(st == obj)
                        return true;
                };
                return false;
            }
        };
        State st = new State(dfa1.initialState,dfa2.initialState);
        Z.add(st);
        int IS = 0;
        for (int i = 0; i < Z.size(); i++) {
            ArrayList<Integer> transition = new ArrayList<Integer>(){

            };
            for (int j = 0; j < dfa1.inputChars.length; j++) {
                if(dfa1.isFinalState(st.x) != -1 || dfa2.isFinalState(st.y.get(0)) != -1)
                {
                    if(Z.indexOf(st) != -1)
                    {
                        if(!FS.contains(Z.indexOf(st)))
                            FS.add(Z.indexOf(st));
                    }
                }

                st = new State();
                st.x =  dfa1.transition(Z.get(i).x,dfa1.inputChars[j]);
                st.y.add(dfa2.transition(Z.get(i).y.get(0),dfa1.inputChars[j]));

                if(!Z.contains(st))
                {
                    Z.add(st);
                }
                transition.add(Z.indexOf(st));
            }

            tt.add(transition);
        }

        return new DFA(IS,FS,tt,Z.size(),dfa1.inputChars.clone());
    }

    DFA dfaConcat(DFA dfa1, DFA dfa2) {
        ArrayList<Integer> FS = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> tt = new ArrayList<>();
        ArrayList<State> Z = new ArrayList<State>() {
            @Override
            public boolean contains(Object o) {
                State obj = (State) o;
                for (State state : this
                        ) {

                    if (obj.x == state.x && obj.y.equals(state.y)) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public int indexOf(Object o) {
                State st = (State) o;
                for (int i = 0; i < this.size(); i++) {
                    if (st.x == this.get(i).x && st.y.equals(this.get(i).y)) {
                        return i;
                    }

//                    if(st.x == this.get(i).x && st.y == this.get(i).y )
//                        return i;
                }
                return super.indexOf(o);
            }
        };
        if (dfa1.isFinalState(dfa1.initialState) != -1) {
            Z.add(new State(dfa1.initialState, dfa2.initialState));
        } else {
            State st = new State();
            st.x = dfa1.initialState;
            Z.add(st);
        }
        for (int i = 0; i < Z.size(); i++) {

            ArrayList<Integer> transitions = new ArrayList<Integer>();

            for (char ch : dfa1.inputChars) {
                State st = new State();
                ArrayList<Integer> y = new ArrayList<>(){
                    @Override
                    public boolean contains(Object o) {
                        int obj = (int) o;
                        for (int num : this
                                ) {

                            if (obj == num) {
                                return true;
                            }
                        }

                        return false;
                    }
                };
                st.x = dfa1.transition(Z.get(i).x, ch);
                if (dfa1.isFinalState(st.x) != -1)
                    y.add(dfa2.initialState);


                for (int j = 0; j < Z.get(i).y.size(); j++) {
                    int yy = dfa2.transition(Z.get(i).y.get(j), ch);
                    if (!y.contains(yy))
                        y.add(yy);
                }
                st.y = y;
                if (!Z.contains(st)) {
                    for (int state :
                            st.y) {
                        if(dfa2.isFinalState(state) != -1)
                        {
                            FS.add(Z.size());
                            break;
                        }
                    }
                    Z.add(st);
                }
                transitions.add(Z.indexOf(st));
            }
            tt.add(transitions);
        }

        return new DFA(dfa1.initialState, FS, tt, Z.size(), dfa1.inputChars.clone());
    }

    DFA dfaClosure(DFA dfa1) throws CloneNotSupportedException {

        ArrayList<Integer> FS = new ArrayList<Integer>();
        FS.add(0);
        ArrayList<ArrayList<Integer>> Z = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> tt = new ArrayList<ArrayList<Integer>>();
        Z.add(new ArrayList<>(){{
            add(dfa1.initialState);
            add(dfa1.noOfStates);
        }});
        for (int i = 0; i < Z.size(); i++) {
            ArrayList<Integer> transitions = new ArrayList<Integer>();
            for (char ch : dfa1.inputChars) {
                ArrayList<Integer> state = new ArrayList<Integer>();
                for (int j = 0; j < Z.get(i).size(); j++) {
                    int st = dfa1.transition(Z.get(i).get(j), ch);
                    if (!state.contains(st))
                    {
                        state.add(st);
                        if(dfa1.isFinalState(st) != -1 && !state.contains(dfa1.initialState))
                                state.add(dfa1.initialState);


                        if(i == 0)
                            break;
                    }
                }
                Collections.sort(state);
                if (!Z.contains(state))
                    Z.add(state);

                transitions.add(Z.indexOf(state));

                for (int st :
                        dfa1.finalState) {
                    if(state.contains(st) && !FS.contains(Z.indexOf(state)))
                        FS.add(Z.indexOf(state));
                }
            }
            tt.add(transitions);
        }

        return new DFA(dfa1.initialState,FS,tt,tt.size(),dfa1.inputChars);
    }

    DFA dfaComplement(DFA dfa) throws CloneNotSupportedException {
        ArrayList<Integer> fs = new ArrayList<>();
        for (int i = 0; i < dfa.noOfStates; i++) {
            if(!dfa.finalState.contains(i))
            {
                fs.add(i);
            }
        }
        DFA dfa2 = (DFA)dfa.clone();
        dfa2.finalState = fs;
        return dfa2;
    }

    DFA dfaIntersection(DFA dfa1, DFA dfa2) throws CloneNotSupportedException {
        DFA dfa1_bar = dfaComplement(dfa1);
        DFA dfa2_bar = dfaComplement(dfa2);
        DFA dfa = dfaOR(dfa1_bar,dfa2_bar);
        DFA dfa_bar = dfaComplement(dfa);
        return dfa_bar;
    }
}
class State {
    int x;
    ArrayList<Integer> y;


    public State() {
        this.y = new ArrayList<Integer>();
    }

    public State(int x, int y) {
        this.x = x;
        this.y = new ArrayList<Integer>();
        this.y.add(y);
    }
}
