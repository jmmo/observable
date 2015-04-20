package org.jmmo.observable.event.handlers;

import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableEvent;

import java.util.List;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 11:02
 */
public abstract class EventHandler4<T1 extends ObservableEvent, T2  extends ObservableEvent, T3 extends ObservableEvent,
                                            T4 extends ObservableEvent> extends EventHandler3<T1, T2, T3> {
    public EventHandler4() {
        this(4);
    }

    protected EventHandler4(int number) {
        super(number);
    }

    @Override
    protected boolean handleEvent(ObservableEvent event, List<Observable> chain) {
        if (super.handleEvent(event, chain)) {
            return true;
        }
        if (classes.get(3).isInstance(event)) {
            handle4((T4) event, chain);
            return true;
        }
        return false;
    }

    protected abstract void handle4(T4 event4, List<Observable> chain);
}
