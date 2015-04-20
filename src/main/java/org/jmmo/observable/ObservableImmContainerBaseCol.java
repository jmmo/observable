package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 12.07.13
 * Time: 10:02
 */
public abstract class ObservableImmContainerBaseCol<E extends Observable> extends ObservableImmContainerBase<E> {
    private final Collection<E> children;

    public ObservableImmContainerBaseCol() {
        this(new ArrayList<E>(1));
    }

    public ObservableImmContainerBaseCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    protected Collection<E> getChildObservables() {
        return children;
    }
}
