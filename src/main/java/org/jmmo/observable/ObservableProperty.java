package org.jmmo.observable;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 20:57
 *
 * <p>Fires events:</p>
 * {@link org.jmmo.observable.event.ChangedValueEvent}<br>
 */
public interface ObservableProperty<T> extends ObservableValue<T> {

    void setValue(T value);
}
