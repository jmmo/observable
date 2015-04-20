package org.jmmo.observable;

import org.jmmo.observable.event.AddedObservableEvent;
import org.jmmo.observable.event.ObservableListener;
import org.jmmo.observable.event.RemovedObservableEvent;

/**
 * User: Tomas
 * Date: 02.01.13
 * Time: 18:06
 *
 * <p>Fires events:</p>
 * {@link AddedObservableEvent}<br>
 * {@link RemovedObservableEvent}<br>
 */
public abstract class ObservableContainerBase<E extends Observable> extends ObservableImmContainerBase<E> {

    protected boolean addChildObservable(E observable) {
        if (addChildToCollection(observable)) {
            onAddChildObservable(observable);
            return true;
        }
        return false;
    }

    protected boolean addChildToCollection(E observable) {
        return getChildObservables().add(observable);
    }

    protected void onAddChildObservable(E observable) {
        doFireAddedObservableEvent(getOwner(), observable);
        //subscribe child
        if (allListeners != null) {
            for (ObservableListener listener : allListeners) {
                observable.addObservableListener(listener);
            }
        }
    }

    protected void doFireAddedObservableEvent(Observable source, Observable involved) {
        fireObservableEvent(new AddedObservableEvent(source, involved));
    }

    protected boolean removeChildObservable(E observable) {
        if (removeChildFromCollection(observable)) {
            onRemoveChildObservable(observable);
            return true;
        }
        return false;
    }

    protected boolean removeChildFromCollection(E observable) {
        return getChildObservables().remove(observable);
    }

    protected void onRemoveChildObservable(E observable) {
        doFireRemovedObservableEvent(getOwner(), observable);
        //unsubscribe child
        if (allListeners != null) {
            for (ObservableListener listener : allListeners) {
                observable.removeObservableListener(listener);
            }
        }
    }

    protected void doFireRemovedObservableEvent(Observable source, Observable involved) {
        fireObservableEvent(new RemovedObservableEvent(source, involved));
    }
}
