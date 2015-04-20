package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 16.02.13
 * Time: 18:42
 */
public abstract class ObservableImmTransparentContainerBaseCol<E extends Observable> extends ObservableImmTransparentContainerBase<E> {
    private final Collection<E> children;

    public ObservableImmTransparentContainerBaseCol() {
        this(new ArrayList<E>(1));
    }

    public ObservableImmTransparentContainerBaseCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    protected Collection<E> getChildObservables() {
        return children;
    }
}
