package org.jmmo.observable.event.handlers;

import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableEvent;

import java.util.List;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 11:10
 */
public abstract class EventHandler5<T1 extends ObservableEvent, T2  extends ObservableEvent, T3 extends ObservableEvent,
                                         T4 extends ObservableEvent, T5 extends ObservableEvent> extends EventHandler4<T1, T2, T3, T4> {
    public EventHandler5() {
        this(5);
    }

    protected EventHandler5(int number) {
        super(number);
    }

    @Override
    protected boolean handleEvent(ObservableEvent event, List<Observable> chain) {
        if (super.handleEvent(event, chain)) {
            return true;
        }
        if (classes.get(4).isInstance(event)) {
            handle5((T5) event, chain);
            return true;
        }
        return false;
    }

    protected abstract void handle5(T5 event5, List<Observable> chain);
}
