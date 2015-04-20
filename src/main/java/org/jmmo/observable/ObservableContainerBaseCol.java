package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 06.01.13
 * Time: 15:12
 */
public abstract class ObservableContainerBaseCol<E extends Observable> extends ObservableContainerBase<E> {
    private final Collection<E> children;

    public ObservableContainerBaseCol() {
        this(new ArrayList<E>(0));
    }

    public ObservableContainerBaseCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    protected Collection<E> getChildObservables() {
        return children;
    }
}
