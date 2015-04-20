package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 12.07.13
 * Time: 10:03
 */
public abstract class ObservableImmContainerSupportCol<E extends Observable> extends ObservableImmContainerSupport<E> {
    private final Collection<E> children;

    public ObservableImmContainerSupportCol() {
        this(new ArrayList<E>(1));
    }

    public ObservableImmContainerSupportCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    public Collection<E> getChildObservables() {
        return children;
    }
}
