package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 19:59
 */
public class RemovedObservableEvent extends AddedOrRemovedObservableEvent {

    public RemovedObservableEvent(Observable source, Observable involved) {
        super(source, involved);
    }
}
