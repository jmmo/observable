package org.jmmo.observable.event.handlers;

import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableEvent;

import java.util.List;

/**
 * User: Tomas
 * Date: 15.02.13
 * Time: 18:53
 */
public abstract class EventHandler2<T1 extends ObservableEvent, T2  extends ObservableEvent> extends EventHandler1<T1> {

    public EventHandler2() {
        this(2);
    }

    protected EventHandler2(int number) {
        super(number);
    }

    @Override
    protected boolean handleEvent(ObservableEvent event, List<Observable> chain) {
        if (super.handleEvent(event, chain)) {
            return true;
        }
        if (classes.get(1).isInstance(event)) {
            handle2((T2) event, chain);
            return true;
        }
        return false;
    }

    protected abstract void handle2(T2 event2, List<Observable> chain);
}
