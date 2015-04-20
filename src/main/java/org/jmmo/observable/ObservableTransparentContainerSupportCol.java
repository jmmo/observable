package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 01.03.13
 * Time: 15:01
 */
public abstract class ObservableTransparentContainerSupportCol<E extends Observable> extends ObservableTransparentContainerSupport<E> {
    private final Collection<E> children;

    public ObservableTransparentContainerSupportCol() {
        this(new ArrayList<E>(0));
    }

    public ObservableTransparentContainerSupportCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    public Collection<E> getChildObservables() {
        return children;
    }
}
