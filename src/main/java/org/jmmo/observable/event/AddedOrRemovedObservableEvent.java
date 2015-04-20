package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

/**
 * User: Tomas
 * Date: 01.01.13
 * Time: 19:50
 */
public abstract class AddedOrRemovedObservableEvent extends ObservableEvent {
    private final Observable involved;

    public AddedOrRemovedObservableEvent(Observable source, Observable involved) {
        super(source);
        this.involved = involved;
    }

    public Observable getInvolved() {
        return involved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddedOrRemovedObservableEvent that = (AddedOrRemovedObservableEvent) o;

        if (!getSource().equals(that.getSource())) return false;
        if (involved != null ? !involved.equals(that.involved) : that.involved != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getSource().hashCode();
        result = 31 * result + (involved != null ? involved.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "source=" + getSource() +
                ", involved=" + getInvolved() +
                '}';
    }
}
