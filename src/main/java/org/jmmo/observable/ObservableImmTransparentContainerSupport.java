package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 18:43
 */
public abstract class ObservableImmTransparentContainerSupport<E extends Observable> extends ObservableImmContainerSupport<E> {

    @Override
    protected ObservableListener createChildListener(ObservableListener listener) {
        return listener;
    }
}
