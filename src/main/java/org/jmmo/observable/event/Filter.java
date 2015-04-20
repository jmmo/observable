package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

/**
 * User: Tomas
 * Date: 19.02.13
 * Time: 20:28
 */
public class Filter {

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable> boolean observable1(Observable observable, Class<T1> class1, Handler1<T1> handler1) {
        return filter(observable, handler1, class1);
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable, T2 extends Observable> boolean observable2(Observable observable, Class<T1> class1,
                                                                         Class<T2> class2, Handler2<T1, T2> handler2) {
        return filter(observable, handler2, class1, class2);
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable> boolean observable3(Observable observable,
                                 Class<T1> class1, Class<T2> class2, Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        return filter(observable, handler3, class1, class2, class3);
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable>
            boolean observable4(Observable observable, Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4,
                                Handler4<T1, T2, T3, T4> handler4) {
        return filter(observable, handler4, class1, class2, class3, class4);
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable> boolean observable5(Observable observable, Class<T1> class1, Class<T2> class2,
                        Class<T3> class3, Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        return filter(observable, handler5, class1, class2, class3, class4, class5);
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable> boolean observable6(Observable observable, Class<T1> class1, Class<T2> class2,
            Class<T3> class3, Class<T4> class4, Class<T5> class5, Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        return filter(observable, handler6, class1, class2, class3, class4, class5, class6);
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends Observable, T2 extends Observable, T3 extends Observable, T4 extends Observable,
            T5 extends Observable, T6 extends Observable, T7 extends Observable> boolean observable7(Observable observable,
            Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Class<T5> class5, Class<T6> class6, Class<T7> class7,
            Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        return filter(observable, handler7, class1, class2, class3, class4, class5, class6, class7);
    }

    protected static boolean filter(Observable observable, HandlerIface1 handler, Class<? extends Observable>... classes) {
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].isInstance(observable)) {
                return handle(handler, i, observable);
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    protected static boolean handle(HandlerIface1 handler, int index, Observable observable) {
        switch (index) {
            case 0: return handler.observable1(observable);
            case 1: return ((HandlerIface2) handler).observable2(observable);
            case 2: return ((HandlerIface3) handler).observable3(observable);
            case 3: return ((HandlerIface4) handler).observable4(observable);
            case 4: return ((HandlerIface5) handler).observable5(observable);
            case 5: return ((HandlerIface6) handler).observable6(observable);
            case 6: return ((HandlerIface7) handler).observable7(observable);
            default: throw new  IllegalArgumentException("No handler for index " + index);
        }
    }

    protected interface HandlerIface1<T1> {
        boolean observable1(T1 observable);
    }
    public static abstract class Handler1<T1 extends Observable> implements HandlerIface1<T1> {}

    protected interface HandlerIface2<T1, T2> extends HandlerIface1<T1> {
        boolean observable2(T2 observable);
    }
    public static abstract class Handler2<T1 extends Observable, T2 extends Observable> implements HandlerIface2<T1, T2> {}

    protected interface HandlerIface3<T1, T2, T3> extends HandlerIface2<T1, T2> {
        boolean observable3(T3 observable);
    }
    public static abstract class Handler3<T1 extends Observable, T2 extends Observable, T3 extends Observable>
            implements HandlerIface3<T1, T2, T3> {}

    protected interface HandlerIface4<T1, T2, T3, T4> extends HandlerIface3<T1, T2, T3> {
        boolean observable4(T4 observable);
    }
    public static abstract class Handler4<T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable> implements HandlerIface4<T1, T2, T3, T4> {}

    protected interface HandlerIface5<T1, T2, T3, T4, T5> extends HandlerIface4<T1, T2, T3, T4> {
        boolean observable5(T5 observable);
    }
    public static abstract class Handler5<T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable, T5 extends Observable> implements HandlerIface5<T1, T2, T3, T4, T5> {}

    protected interface HandlerIface6<T1, T2, T3, T4, T5, T6> extends HandlerIface5<T1, T2, T3, T4, T5> {
        boolean observable6(T6 observable);
    }
    public static abstract class Handler6<T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable, T5 extends Observable, T6 extends Observable> implements HandlerIface6<T1, T2, T3, T4, T5, T6> {}

    protected interface HandlerIface7<T1, T2, T3, T4, T5, T6, T7> extends HandlerIface6<T1, T2, T3, T4, T5, T6> {
        boolean observable7(T7 observable);
    }
    public static abstract class Handler7<T1 extends Observable, T2 extends Observable, T3 extends Observable,
            T4 extends Observable, T5 extends Observable, T6 extends Observable, T7 extends Observable> implements HandlerIface7<T1, T2, T3, T4, T5, T6, T7> {}
}
