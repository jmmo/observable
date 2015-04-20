package org.jmmo.observable;

import org.jmmo.observable.event.ChangedValueEvent;

/**
 * User: Tomas
 * Date: 06.01.13
 * Time: 19:47
 */
public class ObservablePropertyImpl<T> extends ObservablePropertyBase<T> {
    private T value;
    private final String name;

    public ObservablePropertyImpl() {
        this("", null);
    }

    public ObservablePropertyImpl(T initValue) {
        this("", initValue);
    }

    public ObservablePropertyImpl(String name, T initValue) {
        if (name == null) {
            throw new IllegalArgumentException("Name of the Property cannot be null");
        }
        this.name = name;
        this.value = initValue;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    protected void setInnerValue(T value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setValue(T value) {
        if (isNewValue(value)) {
            T oldValue = this.value;
            this.value = value;
            fireObservableEvent(new ChangedValueEvent<T>(this, oldValue, value));
        }
    }

    protected boolean isNewValue(T value) {
        return (value == null && getValue() != null) || (value != null && !value.equals(getValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObservablePropertyImpl)) return false;

        ObservablePropertyImpl that = (ObservablePropertyImpl) o;

        if (!name.equals(that.name)) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ObservablePropertyBase{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}
