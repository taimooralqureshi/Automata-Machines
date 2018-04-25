    public class transition {
        int state;
        String ch;

        public transition(int state, String ch) {
            this.state = state;
            this.ch = ch;
        }

        @Override
        public String toString() {
            return this.state + " " + this.ch;
        }
    }


