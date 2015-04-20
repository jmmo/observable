package org.jmmo.observable;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 20:32
 */
public interface ObservableValue<T> extends Observable {

    T getValue();

    String getName();
}
