package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.util.List;

/**
 * User: Tomas
 * Date: 02.01.13
 * Time: 21:58
 */
public interface ObservableChained {

    List<Observable> getChain();
}
