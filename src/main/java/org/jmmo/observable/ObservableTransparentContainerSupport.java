package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

/**
 * User: Tomas
 * Date: 04.01.13
 * Time: 10:51
 */
public abstract class ObservableTransparentContainerSupport<E extends Observable> extends ObservableContainerSupport<E> {

    @Override
    protected ObservableListener createChildListener(ObservableListener listener) {
        return listener;
    }
}
