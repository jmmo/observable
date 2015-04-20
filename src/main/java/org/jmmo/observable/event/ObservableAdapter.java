package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.util.List;

/**
 * User: Tomas
 * Date: 14.01.13
 * Time: 20:35
 */
public abstract class ObservableAdapter implements ObservableListener {

    @Override
    public boolean filterObservable(Object observable, List<Observable> chain) {
        return true;
    }
}
