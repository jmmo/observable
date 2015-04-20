package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 18:42
 */
public abstract class ObservableImmTransparentContainerBase<E extends Observable> extends ObservableImmContainerBase<E> {

    @Override
    protected ObservableListener createChildListener(ObservableListener listener) {
        return listener;
    }
}
