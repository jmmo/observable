package org.jmmo.observable;

/**
 * User: Tomas
 * Date: 07.01.13
 * Time: 8:52
 * Fire {@link org.jmmo.observable.event.ChangedValueEvent} on any {@link ObservableProperty#setValue(Object)}
 */
public class ObservablePropertyAlways<T> extends ObservablePropertyImpl<T> {

    public ObservablePropertyAlways() {
        super();
    }

    public ObservablePropertyAlways(T initValue) {
        super(initValue);
    }

    public ObservablePropertyAlways(String name, T initValue) {
        super(name, initValue);
    }

    @Override
    protected boolean isNewValue(T value) {
        return true;
    }
}
