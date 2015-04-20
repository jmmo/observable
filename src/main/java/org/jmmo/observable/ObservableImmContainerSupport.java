package org.jmmo.observable;

import org.jmmo.observable.event.ObservableEvent;

import java.util.Collection;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 18:30
 */
public abstract class ObservableImmContainerSupport<E extends Observable> extends ObservableImmContainerBase<E> {

    @Override
    public <T extends ObservableEvent> T fireObservableEvent(T event) {
        return super.fireObservableEvent(event);
    }

    @Override
    public abstract Collection<E> getChildObservables();

    @Override
    protected abstract Observable getOwner();
}
