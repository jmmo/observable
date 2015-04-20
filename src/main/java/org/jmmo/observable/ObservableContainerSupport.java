package org.jmmo.observable;

import org.jmmo.observable.event.ObservableEvent;

import java.util.Collection;

/**
 * User: Tomas
 * Date: 03.01.13
 * Time: 0:30
 */
public abstract class ObservableContainerSupport<E extends Observable> extends ObservableContainerBase<E> {

    @Override
    public boolean addChildObservable(E observable) {
        return super.addChildObservable(observable);
    }

    @Override
    public boolean removeChildObservable(E observable) {
        return super.removeChildObservable(observable);
    }

    @Override
    public void onAddChildObservable(E observable) {
        super.onAddChildObservable(observable);
    }

    @Override
    public void onRemoveChildObservable(E observable) {
        super.onRemoveChildObservable(observable);
    }

    @Override
    public <T extends ObservableEvent> T fireObservableEvent(T event) {
        return super.fireObservableEvent(event);
    }

    @Override
    public abstract Collection<E> getChildObservables();

    @Override
    protected abstract Observable getOwner();
}
