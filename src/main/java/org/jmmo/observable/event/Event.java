package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Tomas
 * Date: 19.02.13
 * Time: 17:00
 */
public class Event {

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent> ObservableListener listener(Class<T1> class1, Handler1<T1> handler1) {
        handler1.setClasses(class1);
        return handler1;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent> ObservableListener listener(Class<T1> class1,
                                                                                                       Class<T2> class2, Handler2<T1, T2> handler2) {
        handler2.setClasses(class1, class2);
        return handler2;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent>
                  ObservableListener listener(Class<T1> class1, Class<T2> class2, Class<T3> class3, Handler3<T1, T2, T3> handler3) {
        handler3.setClasses(class1, class2, class3);
        return handler3;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent, T4 extends ObservableEvent>
    ObservableListener listener(Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Handler4<T1, T2, T3, T4> handler4) {
        handler4.setClasses(class1, class2, class3, class4);
        return handler4;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent> ObservableListener listener(Class<T1> class1, Class<T2> class2, Class<T3> class3,
                                                                                                Class<T4> class4, Class<T5> class5, Handler5<T1, T2, T3, T4, T5> handler5) {
        handler5.setClasses(class1, class2, class3, class4, class5);
        return handler5;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent, T6 extends ObservableEvent>
            ObservableListener listener(Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Class<T5> class5,
            Class<T6> class6, Handler6<T1, T2, T3, T4, T5, T6> handler6) {
        handler6.setClasses(class1, class2, class3, class4, class5, class6);
        return handler6;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent, T6 extends ObservableEvent, T7 extends ObservableEvent>
            ObservableListener listener( Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Class<T5> class5,
                                    Class<T6> class6, Class<T7> class7, Handler7<T1, T2, T3, T4, T5, T6, T7> handler7) {
        handler7.setClasses(class1, class2, class3, class4, class5, class6, class7);
        return handler7;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent, T4 extends ObservableEvent,
            T5 extends ObservableEvent, T6 extends ObservableEvent, T7 extends ObservableEvent, T8 extends ObservableEvent>
            ObservableListener listener( Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Class<T5> class5,
                                  Class<T6> class6, Class<T7> class7, Class<T8> class8, Handler8<T1, T2, T3, T4, T5, T6, T7, T8> handler8) {
        handler8.setClasses(class1, class2, class3, class4, class5, class6, class7, class8);
        return handler8;
    }

    @SuppressWarnings("unchecked")
    public static <T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent, T4 extends ObservableEvent,
            T5 extends ObservableEvent, T6 extends ObservableEvent, T7 extends ObservableEvent, T8 extends ObservableEvent, T9 extends ObservableEvent>
            ObservableListener listener( Class<T1> class1, Class<T2> class2, Class<T3> class3, Class<T4> class4, Class<T5> class5,Class<T6> class6,
                                          Class<T7> class7, Class<T8> class8, Class<T9> class9, Handler9<T1, T2, T3, T4, T5, T6, T7, T8, T9> handler9) {
        handler9.setClasses(class1, class2, class3, class4, class5, class6, class7, class8, class9);
        return handler9;
    }

    protected interface HandlerIface1<T1> extends ObservableListener {
        void event1(T1 event, List<Observable> chain);
    }
    protected static abstract class HandlerBase<T1> implements HandlerIface1<T1> {
        private Class<? extends ObservableEvent>[] classes;

        protected final void setClasses(Class<? extends ObservableEvent> ...classes) {
            if (this.classes != null) {
                throw new IllegalStateException("Classes is always set to " + this);
            }
            this.classes = classes;
        }

        @Override
        public final void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].isInstance(event)) {
                    handle(i, event, chain);
                    break;
                }
            }
        }

        @SuppressWarnings("unchecked")
        protected final void handle(int index, ObservableEvent event, List<Observable> chain) {
            switch (index) {
                case 0: ((HandlerIface1) this).event1(event, chain); break;
                case 1: ((HandlerIface2) this).event2(event, chain); break;
                case 2: ((HandlerIface3) this).event3(event, chain); break;
                case 3: ((HandlerIface4) this).event4(event, chain); break;
                case 4: ((HandlerIface5) this).event5(event, chain); break;
                case 5: ((HandlerIface6) this).event6(event, chain); break;
                case 6: ((HandlerIface7) this).event7(event, chain); break;
                case 7: ((HandlerIface8) this).event8(event, chain); break;
                case 8: ((HandlerIface9) this).event9(event, chain); break;
                default: throw new  IllegalArgumentException("No handler for index " + index);
            }
        }

        @Override
        public boolean filterObservable(Object observable, List<Observable> chain) {
            return true;
        }
    }
    public static abstract class Handler1<T1 extends ObservableEvent> extends HandlerBase<T1> {}

    protected interface HandlerIface2<T1, T2> extends HandlerIface1<T1> {
        void event2(T2 event, List<Observable> chain);
    }
    public static abstract class Handler2<T1 extends ObservableEvent, T2 extends ObservableEvent> extends HandlerBase<T1> implements HandlerIface2<T1, T2> {}

    protected interface HandlerIface3<T1, T2, T3> extends HandlerIface2<T1, T2> {
        void event3(T3 event, List<Observable> chain);
    }
    public static abstract class Handler3<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent>
                                                     extends HandlerBase<T1> implements HandlerIface3<T1, T2, T3> {}

    protected interface HandlerIface4<T1, T2, T3, T4> extends HandlerIface3<T1, T2, T3> {
        void event4(T4 event, List<Observable> chain);
    }
    public static abstract class Handler4<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
                      T4 extends ObservableEvent> extends HandlerBase<T1> implements HandlerIface4<T1, T2, T3, T4> {}

    protected interface HandlerIface5<T1, T2, T3, T4, T5> extends HandlerIface4<T1, T2, T3, T4> {
        void event5(T5 event, List<Observable> chain);
    }
    public static abstract class Handler5<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
                 T4 extends ObservableEvent, T5 extends ObservableEvent> extends HandlerBase<T1> implements HandlerIface5<T1, T2, T3, T4, T5> {}

    protected interface HandlerIface6<T1, T2, T3, T4, T5, T6> extends HandlerIface5<T1, T2, T3, T4, T5> {
        void event6(T6 event, List<Observable> chain);
    }
    public static abstract class Handler6<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent, T6 extends ObservableEvent>
            extends HandlerBase<T1> implements HandlerIface6<T1, T2, T3, T4, T5, T6> {}

    protected interface HandlerIface7<T1, T2, T3, T4, T5, T6, T7> extends HandlerIface6<T1, T2, T3, T4, T5, T6> {
        void event7(T7 event, List<Observable> chain);
    }
    public static abstract class Handler7<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent, T6 extends ObservableEvent, T7 extends ObservableEvent>
            extends HandlerBase<T1> implements HandlerIface7<T1, T2, T3, T4, T5, T6, T7> {}

    protected interface HandlerIface8<T1, T2, T3, T4, T5, T6, T7, T8> extends HandlerIface7<T1, T2, T3, T4, T5, T6, T7> {
        void event8(T8 event, List<Observable> chain);
    }
    public static abstract class Handler8<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent, T6 extends ObservableEvent, T7 extends ObservableEvent, T8 extends ObservableEvent>
            extends HandlerBase<T1> implements HandlerIface8<T1, T2, T3, T4, T5, T6, T7, T8> {}

    protected interface HandlerIface9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends HandlerIface8<T1, T2, T3, T4, T5, T6, T7, T8> {
        void event9(T9 event, List<Observable> chain);
    }
    public static abstract class Handler9<T1 extends ObservableEvent, T2 extends ObservableEvent, T3 extends ObservableEvent,
            T4 extends ObservableEvent, T5 extends ObservableEvent, T6 extends ObservableEvent, T7 extends ObservableEvent,
            T8 extends ObservableEvent, T9 extends ObservableEvent>
            extends HandlerBase<T1> implements HandlerIface9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {}

    //Multi args listener

    public static ObservableListener listener(final ObservableFilter filter, final ObservableHandler...handlers) {
        final List<Class<? extends ObservableEvent>> classes = new ArrayList<Class<? extends ObservableEvent>>(handlers.length);
        for (ObservableHandler handler : handlers) {
            classes.add(getEventClass(handler.getClass()));
        }

        return new ObservableListener() {
            @SuppressWarnings("unchecked")
            @Override
            public boolean filterObservable(Object observable, List<Observable> chain) {
                return filter.filterObservable(observable, chain);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                for (int i = 0; i < handlers.length; i++) {
                    if (classes.get(i).isInstance(event)) {
                        handlers[i].handleObservableEvent(event, chain);
                        break;
                    }
                }
            }
        };
    }

    public static ObservableListener listener(final ObservableHandler...handlers) {
        return listener(ObservableFilter.PassAll, handlers);
    }

    @SuppressWarnings("unchecked")
    protected static Class<? extends ObservableEvent> getEventClass(Class<? extends ObservableHandler> clazz) {
        return ListenerBuilder.getTypeArgument(ObservableHandler.class, clazz);
    }
}
