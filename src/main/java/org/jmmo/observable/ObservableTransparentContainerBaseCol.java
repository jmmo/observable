package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 01.03.13
 * Time: 14:59
 */
public abstract class ObservableTransparentContainerBaseCol<E extends Observable> extends ObservableTransparentContainerBase<E> {
    private final Collection<E> children;

    public ObservableTransparentContainerBaseCol() {
        this(new ArrayList<E>(0));
    }

    public ObservableTransparentContainerBaseCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    protected Collection<E> getChildObservables() {
        return children;
    }
}
