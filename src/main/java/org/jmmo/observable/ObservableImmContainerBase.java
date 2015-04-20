package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

import java.util.Collection;
import java.util.Set;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 18:28
 */
public abstract class ObservableImmContainerBase<E extends Observable> extends ObservableBase {
    protected Set<ObservableListener> allListeners;

    @Override
    protected void addChildListeners(ObservableListener listener) {
        final ObservableListener childListener = createChildListener(listener);

        if (allListeners == null) {
            allListeners = createListenersSet();
        }
        if (allListeners.contains(childListener)) {
            throw new IllegalArgumentException("Observable listener " + childListener + " already exists in " + this);
        }
        allListeners.add(childListener);

        //subscribe children
        for (Observable child : getChildObservables()) {
            child.addObservableListener(childListener);
        }
    }

    @Override
    protected void removeChildListeners(ObservableListener listener) {
        if (allListeners != null) {
            final ObservableListener childListener = createChildListener(listener);
            allListeners.remove(childListener);
            if (allListeners.isEmpty()) {
                allListeners = null;
            }

            //unsubscribe children
            for (Observable child : getChildObservables()) {
                child.removeObservableListener(childListener);
            }
        }
    }

    protected ObservableListener createChildListener(ObservableListener listener) {
        return new ObservableListenerWrapper(listener, getOwner());
    }

    protected abstract Collection<E> getChildObservables();
}
