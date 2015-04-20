package org.jmmo.observable;

import org.jmmo.observable.event.ChangedValueEvent;

/**
 * User: Tomas
 * Date: 06.01.13
 * Time: 19:47
 */
public abstract class ObservablePropertyBase<T> extends ObservableBase implements ObservableProperty<T> {

    @Override
    public abstract T getValue();

    protected abstract void setInnerValue(T value);

    @Override
    public abstract String getName();

    @Override
    public void setValue(T value) {
        if (isNewValue(value)) {
            T oldValue = getValue();
            setInnerValue(value);
            fireObservableEvent(new ChangedValueEvent<T>(this, oldValue, value));
        }
    }

    protected boolean isNewValue(T value) {
        return (value == null && getValue() != null) || (value != null && !value.equals(getValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObservablePropertyBase)) return false;

        ObservablePropertyBase that = (ObservablePropertyBase) o;

        if (!getName().equals(that.getName())) return false;
        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getValue() != null ? getValue().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ObservablePropertyBase{" +
                "name=" + getName() +
                ", value=" + getValue() +
                '}';
    }
}
