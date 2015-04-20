package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 06.01.13
 * Time: 15:18
 */
public abstract class ObservableContainerSupportCol<E extends Observable> extends ObservableContainerSupport<E> {
    private final Collection<E> children;

    public ObservableContainerSupportCol() {
        this(new ArrayList<E>(0));
    }

    public ObservableContainerSupportCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    public Collection<E> getChildObservables() {
        return children;
    }
}
