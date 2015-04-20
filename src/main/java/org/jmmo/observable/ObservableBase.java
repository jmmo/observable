package org.jmmo.observable;

import org.jmmo.observable.event.ObservableEvent;
import org.jmmo.observable.event.ObservableListener;

import java.util.*;

/**
 * User: Tomas
 * Date: 02.01.13
 * Time: 0:18
 */
public abstract class ObservableBase implements Observable {
    protected Set<ObservableListener> subscribedListeners;

    @Override
    public void addObservableListener(ObservableListener listener) {
        if (subscribedListeners != null && subscribedListeners.contains(listener)) {
            throw new IllegalArgumentException("Observable listener " + listener + " already exists in " + this);
        }
        if (listener.filterObservable(getOwner(), ObservableListenerWrapper.getListenerChain(listener))) {
            if (subscribedListeners == null) {
                subscribedListeners = createListenersSet();
            }
            subscribedListeners.add(listener);
        }
        addChildListeners(listener);
    }

    protected void addChildListeners(ObservableListener listener) {
    }

    @Override
    public void removeObservableListener(ObservableListener listener) {
        if (subscribedListeners != null) {
            subscribedListeners.remove(listener);
            if (subscribedListeners.isEmpty()) {
                subscribedListeners = null;
            }
        }
        removeChildListeners(listener);
    }

    protected void removeChildListeners(ObservableListener listener) {
    }

    protected <T extends ObservableEvent> T fireObservableEvent(T event) {
        if (subscribedListeners != null) {
            for (ObservableListener listener : subscribedListeners.toArray(new ObservableListener[subscribedListeners.size()])) {
                listener.handleObservableEvent(event, Collections.<Observable>emptyList());
            }
        }

        return event;
    }

    protected Set<ObservableListener> createListenersSet() {
        return new HashSet<ObservableListener>();
    }

    protected Observable getOwner() {
        return this;
    }
}
