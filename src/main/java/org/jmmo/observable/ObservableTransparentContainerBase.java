package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

/**
 * User: Tomas
 * Date: 04.01.13
 * Time: 10:46
 */
public abstract class ObservableTransparentContainerBase<E extends Observable> extends ObservableContainerBase<E> {

    @Override
    protected ObservableListener createChildListener(ObservableListener listener) {
        return listener;
    }
}
