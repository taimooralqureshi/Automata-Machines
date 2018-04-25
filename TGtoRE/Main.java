import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.String.format;

public class Main {

    public static void main(String[] args) {
//         write your code here


            ArrayList<ArrayList<transition>> tt = new ArrayList<ArrayList<transition>>(){{
                add(new ArrayList<transition>(){{
                    add(new transition(0,"a"));
                    add(new transition(1,"a"));
                    add(new transition(2,"a"));
                    add(new transition(0,"b"));
                }});
                add(new ArrayList<transition>(){{
                    add(new transition(2,"a"));
                }});
                add(new ArrayList<transition>(){{
                    add(new transition(2,"a"));
                    add(new transition(2,"b"));
                }});
            }};

                    TG tg = new TG(new ArrayList<Integer>(){{add(0);}} ,new ArrayList<Integer>(){{add(2);}}, tt);

            System.out.println(tg.toRE());

            ArrayList<ArrayList<transition>> tt2 = new ArrayList<ArrayList<transition>>(){{
                add(new ArrayList<transition>(){{
                    add(new transition(1,"a"));
                    add(new transition(0,"b"));
                    add(new transition(1,"b"));
                }});
                add(new ArrayList<transition>(){{
                    add(new transition(2,"b"));
                }});
                add(new ArrayList<transition>(){{
                    add(new transition(3,"a"));
                    add(new transition(1,"b"));
                }});
                add(new ArrayList<transition>(){{
                    add(new transition(3,"a"));
                    add(new transition(3,"b"));
                }});
            }};

            TG tg2 = new TG(new ArrayList<Integer>(){{add(0);}} ,new ArrayList<Integer>(){{add(3);}}, tt2);

            System.out.println(tg2.toRE());

        ArrayList<ArrayList<transition>> tt3 = new ArrayList<ArrayList<transition>>(){{
            add(new ArrayList<transition>(){{
                add(new transition(1,"a"));

            }});
            add(new ArrayList<transition>(){{
                add(new transition(1,"b"));
                add(new transition(2,"bb"));
            }});
            add(new ArrayList<transition>(){{
                add(new transition(2,"a"));
            }});
            add(new ArrayList<transition>(){{
                add(new transition(1,"aa"));
                add(new transition(1,"bb"));
                add(new transition(4,"ba"));
            }});
            add(new ArrayList<transition>(){{
                add(new transition(2,"b"));

            }});
        }};

        TG tg3 = new TG(new ArrayList<Integer>(){{add(0);add(3);}} ,new ArrayList<Integer>(){{add(2);add(4);}}, tt3);

        System.out.println(tg3.toRE());


    }

}
