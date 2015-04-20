package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Tomas
 * Date: 19.02.13
 * Time: 15:33
 */
@SuppressWarnings("unchecked")
public class Chain {

    //--------------------------------------------------- 1 ------------------------------------------------------------
    public static <T1 extends Observable> void exact(List<Observable> chain, Class<T1> class1, Handler1<T1> handler1) {
        if (chain.size() == 1) {
            if (class1.isInstance(chain.get(0))) {
                handler1.chain((T1) chain.get(0));
            }
        }
    }
    public static <T1 extends Observable> void first(List<Observable> chain, Class<T1> class1, Handler1<T1> handler1) {
        if (chain.size() >= 1) {
            if (class1.isInstance(chain.get(0))) {
                handler1.chain((T1) chain.get(0));
            }
        }
    }
    public static <T1 extends Observable> void last(List<Observable> chain, Class<T1> class1, Handler1<T1> handler1) {
        final int size = chain.size();
        if (size >= 1) {
            final Observable elem1 = chain.get(size - 1);
            if (class1.isInstance(elem1)) {
                handler1.chain((T1) elem1);
            }
        }
    }
    public static <T1 extends Observable> void contains(List<Observable> chain, Class<T1> class1, Handler1<T1> handler1) {
        for (Observable observable : chain) {
            if (class1.isInstance(observable)) {
                handler1.chain((T1) observable);
                return;
            }
        }
    }
    public interface Handler1<T1 extends Observable> {
        public void chain(T1 t1);
    }

    //--------------------------------------------------- 2 ------------------------------------------------------------
    public static <T1 extends Observable, T2 extends Observable> void exact(List<Observable> chain, Class<T1> class1, Class<T2> class2, Handler2<T1, T2> handler2) {
        if (chain.size() == 2) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1))) {
                handler2.chain((T1) chain.get(0), (T2) chain.get(1));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable> void first(List<Observable> chain, Class<T1> class1, Class<T2> class2, Handler2<T1, T2> handler2) {
        if (chain.size() >= 2) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1))) {
                handler2.chain((T1) chain.get(0), (T2) chain.get(1));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable> void last(List<Observable> chain, Class<T1> class1, Class<T2> class2, Handler2<T1, T2> handler2) {
        final int size = chain.size();
        if (size >= 2) {
            final Observable elem1 = chain.get(size - 2);
            final Observable elem2 = chain.get(size - 1);
            if (class1.isInstance(elem1) && class2.isInstance(elem2)) {
                handler2.chain((T1) elem1, (T2) elem2);
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable> void contains(List<Observable> chain, Class<T1> class1, Class<T2> class2, Handler2<T1, T2> handler2) {
        final List<Observable> result = new ArrayList<Observable>();
        if (find(chain, Arrays.asList(class1, class2), result)) {
            handler2.chain((T1) result.get(0), (T2) result.get(1));
        }
    }
    public interface Handler2<T1 extends Observable, T2 extends Observable> {
        public void chain(T1 t1, T2 t2);
    }

    //--------------------------------------------------- 3 ------------------------------------------------------------
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable> void exact(List<Observable> chain, Class<T1> class1,
                                                                                                   Class<T2> class2, Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        if (chain.size() == 3) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2))) {
                handler3.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable> void first(List<Observable> chain, Class<T1> class1,
                                                                                                   Class<T2> class2, Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        if (chain.size() >= 3) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2))) {
                handler3.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable> void last(List<Observable> chain, Class<T1> class1,
                                                                                                  Class<T2> class2, Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        final int size = chain.size();
        if (size >= 3) {
            final Observable elem1 = chain.get(size - 3);
            final Observable elem2 = chain.get(size - 2);
            final Observable elem3 = chain.get(size - 1);
            if (class1.isInstance(elem1) && class2.isInstance(elem2) && class3.isInstance(elem3)) {
                handler3.chain((T1) elem1, (T2) elem2, (T3) elem3);
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable> void contains(List<Observable> chain, Class<T1> class1,
                                                                                                      Class<T2> class2, Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        final List<Observable> result = new ArrayList<Observable>();
        if (find(chain, Arrays.asList(class1, class2, class3), result)) {
            handler3.chain((T1) result.get(0), (T2) result.get(1), (T3) result.get(2));
        }
    }
    public interface Handler3<T1 extends Observable, T2 extends Observable, T3 extends Observable> {
        public void chain(T1 t1, T2 t2, T3 t3);
    }

    //--------------------------------------------------- 4 ------------------------------------------------------------
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable> void exact(List<Observable> chain,
                                               Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Handler4<T1, T2, T3, T4> handler4) {
        if (chain.size() == 4) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) && class4.isInstance(chain.get(3))) {
                handler4.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable> void first(List<Observable> chain,
                                               Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Handler4<T1, T2, T3, T4> handler4) {
        if (chain.size() >= 4) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) && class4.isInstance(chain.get(3))) {
                handler4.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable> void last(List<Observable> chain,
                                              Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Handler4<T1, T2, T3, T4> handler4) {
        final int size = chain.size();
        if (size >= 4) {
            final Observable elem1 = chain.get(size - 4);
            final Observable elem2 = chain.get(size - 3);
            final Observable elem3 = chain.get(size - 2);
            final Observable elem4 = chain.get(size - 1);
            if (class1.isInstance(elem1) && class2.isInstance(elem2) && class3.isInstance(elem3) && class4.isInstance(elem4)) {
                handler4.chain((T1) elem1, (T2) elem2, (T3) elem3, (T4) elem4);
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable> void contains(List<Observable> chain,
                                                  Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Handler4<T1, T2, T3, T4> handler4) {
        final List<Observable> result = new ArrayList<Observable>();
        if (find(chain, Arrays.asList(class1, class2, class3, class4), result)) {
            handler4.chain((T1) result.get(0), (T2) result.get(1), (T3) result.get(2), (T4) result.get(3));
        }
    }
    public interface Handler4<T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable> {
        public void chain(T1 t1, T2 t2, T3 t3, T4 t4);
    }

    //--------------------------------------------------- 5 ------------------------------------------------------------
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable> void exact(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                               Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        if (chain.size() == 5) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) &&
                    class4.isInstance(chain.get(3)) && class5.isInstance(chain.get(4))) {
                handler5.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3), (T5) chain.get(4));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable> void first(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                               Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        if (chain.size() >= 5) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) &&
                    class4.isInstance(chain.get(3)) && class5.isInstance(chain.get(4))) {
                handler5.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3), (T5) chain.get(4));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable> void last(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                              Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        final int size = chain.size();
        if (size >= 5) {
            final Observable elem1 = chain.get(size - 5);
            final Observable elem2 = chain.get(size - 4);
            final Observable elem3 = chain.get(size - 3);
            final Observable elem4 = chain.get(size - 2);
            final Observable elem5 = chain.get(size - 1);
            if (class1.isInstance(elem1) && class2.isInstance(elem2) && class3.isInstance(elem3) &&
                    class4.isInstance(elem4) && class5.isInstance(elem5)) {
                handler5.chain((T1) elem1, (T2) elem2, (T3) elem3, (T4) elem4, (T5) elem5);
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable> void contains(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                  Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        final List<Observable> result = new ArrayList<Observable>();
        if (find(chain, Arrays.asList(class1, class2, class3, class4, class5), result)) {
            handler5.chain((T1) result.get(0), (T2) result.get(1), (T3) result.get(2), (T4) result.get(3), (T5) result.get(4));
        }
    }
    public interface Handler5<T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable, T5 extends Observable> {
        public void chain(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
    }

    //--------------------------------------------------- 6 ------------------------------------------------------------
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable> void exact(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                      Class<T4> class4, Class<T5> class5, Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        if (chain.size() == 6) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) &&
                    class4.isInstance(chain.get(3)) && class5.isInstance(chain.get(4)) && class6.isInstance(chain.get(5))) {
                handler6.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3), (T5) chain.get(4), (T6) chain.get(5));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable> void first(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                      Class<T4> class4, Class<T5> class5, Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        if (chain.size() >= 6) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) &&
                    class4.isInstance(chain.get(3)) && class5.isInstance(chain.get(4)) && class6.isInstance(chain.get(5))) {
                handler6.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3), (T5) chain.get(4), (T6) chain.get(5));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable> void last(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                     Class<T4> class4, Class<T5> class5, Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        final int size = chain.size();
        if (size >= 6) {
            final Observable elem1 = chain.get(size - 6);
            final Observable elem2 = chain.get(size - 5);
            final Observable elem3 = chain.get(size - 4);
            final Observable elem4 = chain.get(size - 3);
            final Observable elem5 = chain.get(size - 2);
            final Observable elem6 = chain.get(size - 1);
            if (class1.isInstance(elem1) && class2.isInstance(elem2) && class3.isInstance(elem3) &&
                    class4.isInstance(elem4) && class5.isInstance(elem5) && class6.isInstance(elem6)) {
                handler6.chain((T1) elem1, (T2) elem2, (T3) elem3, (T4) elem4, (T5) elem5, (T6) elem6);
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable> void contains(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                  Class<T4> class4, Class<T5> class5, Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        final List<Observable> result = new ArrayList<Observable>();
        if (find(chain, Arrays.asList(class1, class2, class3, class4, class5, class6), result)) {
            handler6.chain((T1) result.get(0), (T2) result.get(1), (T3) result.get(2), (T4) result.get(3), (T5) result.get(4), (T6) result.get(5));
        }
    }
    public interface Handler6<T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable> {
        public void chain(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
    }

    //--------------------------------------------------- 7 ------------------------------------------------------------
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable, T7 extends Observable> void exact(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                                             Class<T4> class4, Class<T5> class5, Class<T6> class6, Class<T7> class7, Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        if (chain.size() == 7) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) &&
                    class4.isInstance(chain.get(3)) && class5.isInstance(chain.get(4)) && class6.isInstance(chain.get(5)) && class7.isInstance(chain.get(6))) {
                handler7.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3), (T5) chain.get(4), (T6) chain.get(5), (T7) chain.get(7));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable, T7 extends Observable> void first(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                                             Class<T4> class4, Class<T5> class5, Class<T6> class6, Class<T7> class7, Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        if (chain.size() >= 7) {
            if (class1.isInstance(chain.get(0)) && class2.isInstance(chain.get(1)) && class3.isInstance(chain.get(2)) &&
                    class4.isInstance(chain.get(3)) && class5.isInstance(chain.get(4)) && class6.isInstance(chain.get(5)) && class7.isInstance(chain.get(6))) {
                handler7.chain((T1) chain.get(0), (T2) chain.get(1), (T3) chain.get(2), (T4) chain.get(3), (T5) chain.get(4), (T6) chain.get(5), (T7) chain.get(7));
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable, T7 extends Observable> void last(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                                            Class<T4> class4, Class<T5> class5, Class<T6> class6, Class<T7> class7, Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        final int size = chain.size();
        if (size >= 7) {
            final Observable elem1 = chain.get(size - 7);
            final Observable elem2 = chain.get(size - 6);
            final Observable elem3 = chain.get(size - 5);
            final Observable elem4 = chain.get(size - 4);
            final Observable elem5 = chain.get(size - 3);
            final Observable elem6 = chain.get(size - 2);
            final Observable elem7 = chain.get(size - 1);
            if (class1.isInstance(elem1) && class2.isInstance(elem2) && class3.isInstance(elem3) &&
                    class4.isInstance(elem4) && class5.isInstance(elem5) && class6.isInstance(elem6) && class7.isInstance(elem7)) {
                handler7.chain((T1) elem1, (T2) elem2, (T3) elem3, (T4) elem4, (T5) elem5, (T6) elem6, (T7) elem7);
            }
        }
    }
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable, T7 extends Observable> void contains(List<Observable> chain, Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                         Class<T4> class4, Class<T5> class5, Class<T6> class6, Class<T7> class7, Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        final List<Observable> result = new ArrayList<Observable>();
        if (find(chain, Arrays.asList(class1, class2, class3, class4, class5, class6, class7), result)) {
            handler7.chain((T1) result.get(0), (T2) result.get(1), (T3) result.get(2), (T4) result.get(3), (T5) result.get(4), (T6) result.get(5), (T7) result.get(6));
        }
    }
    public interface Handler7<T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable, T7 extends Observable> {
        public void chain(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
    }

    private static boolean find(List<Observable> chain, List<Class<? extends Observable>> classes, List<Observable> result) {
        int found = -1;
        for (int i = 0; i < chain.size(); i++) {
            if (classes.get(0).isInstance(chain.get(i))) {
                found = i;
                break;
            }
        }

        if (found < 0) {
            return false;
        }

        result.add(chain.get(found));

        if (classes.size() < 2) {
            return true;
        }

        final List<Observable> newChain = new ArrayList<Observable>(chain);
        newChain.remove(found);
        final List<Class<? extends Observable>> newClasses = new ArrayList<Class<? extends Observable>>(classes);
        newClasses.remove(0);
        return find(newChain, newClasses, result);
    }
}
