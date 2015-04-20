package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.util.List;

/**
 * User: Tomas
 * Date: 09.03.13
 * Time: 20:56
 */
public interface ObservableHandler<T extends ObservableEvent> {

    void handleObservableEvent(T event, List<Observable> chain);
}
