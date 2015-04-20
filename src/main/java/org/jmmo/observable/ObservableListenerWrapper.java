package org.jmmo.observable;

import org.jmmo.observable.event.ListenerWrapper;
import org.jmmo.observable.event.ObservableChained;
import org.jmmo.observable.event.ObservableEvent;
import org.jmmo.observable.event.ObservableListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Tomas
 * Date: 02.01.13
 * Time: 20:05
 */
public class ObservableListenerWrapper implements ObservableListener, ListenerWrapper, ObservableChained {
    private final ObservableListener wrapped;
    private final List<Observable> chain;

    public ObservableListenerWrapper(ObservableListener wrapped, List<Observable> chain) {
        this.wrapped = wrapped;
        this.chain = chain;
    }

    public ObservableListenerWrapper(ObservableListener listener, Observable observable) {
        this.wrapped = getWrappedListener(listener);
        final List<Observable> listenerChain = copyObservables(getListenerChain(listener));
        listenerChain.add(observable);
        this.chain = Collections.unmodifiableList(listenerChain);
    }

    @Override
    public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
        getWrapped().handleObservableEvent(event, getChain());
    }

    @Override
    public boolean filterObservable(Object observable, List<Observable> chain) {
        return getWrapped().filterObservable(observable, chain);
    }

    @Override
    public ObservableListener getWrapped() {
        return wrapped;
    }

    @Override
    public List<Observable> getChain() {
        return chain;
    }

    protected List<Observable> copyObservables(List<Observable> observables) {
        List<Observable> result = new ArrayList<Observable>(observables.size() + 1);
        result.addAll(observables);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObservableListenerWrapper that = (ObservableListenerWrapper) o;

        if (!chain.equals(that.chain)) return false;
        if (!wrapped.equals(that.wrapped)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wrapped.hashCode();
        result = 31 * result + chain.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ObservableListenerWrapper{" +
                "wrapped=" + wrapped +
                ", chain=" + chain +
                '}';
    }

    public static ObservableListener getWrappedListener(ObservableListener listener) {
        return listener instanceof ListenerWrapper ? ((ListenerWrapper) listener).getWrapped() : listener;
    }

    public static List<Observable> getListenerChain(ObservableListener listener) {
        return listener instanceof ObservableChained ? ((ObservableChained) listener).getChain() : Collections.<Observable>emptyList();
    }
}
