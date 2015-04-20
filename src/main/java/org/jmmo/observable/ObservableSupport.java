package org.jmmo.observable;

import org.jmmo.observable.event.ObservableEvent;

/**
 * User: Tomas
 * Date: 02.01.13
 * Time: 0:18
 */
public abstract class ObservableSupport extends ObservableBase {

    @Override
    public <T extends ObservableEvent> T fireObservableEvent(T event) {
         return super.fireObservableEvent(event);
    }

    @Override
    protected abstract Observable getOwner();
}
