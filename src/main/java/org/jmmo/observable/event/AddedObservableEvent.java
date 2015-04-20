package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 19:58
 */
public class AddedObservableEvent extends AddedOrRemovedObservableEvent {

    public AddedObservableEvent(Observable source, Observable involved) {
        super(source, involved);
    }
}
