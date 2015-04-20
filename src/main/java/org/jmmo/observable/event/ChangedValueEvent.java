package org.jmmo.observable.event;

import org.jmmo.observable.ObservableValue;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 20:02
 */
public class ChangedValueEvent<T> extends ObservableEvent {
    private final T oldValue;
    private final T newValue;

    public ChangedValueEvent(ObservableValue<T> source, T oldValue) {
        this(source, oldValue, source.getValue());
    }

    public ChangedValueEvent(ObservableValue<T> source, T oldValue, T newValue) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public T getOldValue() {
        return oldValue;
    }

    public T getNewValue() {
        return newValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObservableValue<T> getSource() {
        return (ObservableValue<T>) super.getSource();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangedValueEvent that = (ChangedValueEvent) o;

        if (!getSource().equals(that.getSource())) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getSource().hashCode();
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "source=" + getSource() +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                '}';
    }
}
