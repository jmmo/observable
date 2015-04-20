package org.jmmo.observable.event;

import java.util.EventObject;

/**
 * User: Tomas
 * Date: 30.12.12
 * Time: 22:53
 * It is Event which includes a chain Observables through which it has passed.
 */
public class ObservableEvent extends EventObject {

    public ObservableEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "source=" + getSource() +
                '}';
    }
}
