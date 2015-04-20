package org.jmmo.observable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Tomas
 * Date: 01.03.13
 * Time: 14:57
 */
public abstract class ObservableImmTransparentContainerSupportCol<E extends Observable> extends ObservableImmTransparentContainerSupport<E> {
    private final Collection<E> children;

    public ObservableImmTransparentContainerSupportCol() {
        this(new ArrayList<E>(1));
    }

    public ObservableImmTransparentContainerSupportCol(Collection<E> children) {
        this.children = children;
    }

    @Override
    public Collection<E> getChildObservables() {
        return children;
    }
}
