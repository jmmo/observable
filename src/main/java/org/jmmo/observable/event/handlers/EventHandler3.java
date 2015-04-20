package org.jmmo.observable.event.handlers;

import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableEvent;

import java.util.List;

/**
 * User: Tomas
 * Date: 15.02.13
 * Time: 18:53
 */
public abstract class EventHandler3<T1 extends ObservableEvent, T2  extends ObservableEvent, T3 extends ObservableEvent>
                                       extends EventHandler2<T1, T2> {
    public EventHandler3() {
        this(3);
    }

    protected EventHandler3(int number) {
        super(number);
    }

    @Override
    protected boolean handleEvent(ObservableEvent event, List<Observable> chain) {
        if (super.handleEvent(event, chain)) {
            return true;
        }
        if (classes.get(2).isInstance(event)) {
            handle3((T3) event, chain);
            return true;
        }
        return false;
    }

    protected abstract void handle3(T3 event3, List<Observable> chain);
}
