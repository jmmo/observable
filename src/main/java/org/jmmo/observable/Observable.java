package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

/**
 * User: Tomas
 * Date: 30.12.12
 * Time: 21:36
 */
public interface Observable {

    void addObservableListener(ObservableListener listener);

    void removeObservableListener(ObservableListener listener);
}
