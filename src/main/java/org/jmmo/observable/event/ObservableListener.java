package org.jmmo.observable.event;

import java.util.EventListener;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 19:29
 */
public interface ObservableListener extends EventListener, ObservableHandler<ObservableEvent>, ObservableFilter<Object> {
}
