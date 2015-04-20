package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.util.List;

/**
 * User: Tomas
 * Date: 09.03.13
 * Time: 20:57
 */
public interface ObservableFilter<T> {

    boolean filterObservable(T observable, List<Observable> chain);

    public static final ObservableFilter<Observable> PassAll = new ObservableFilter<Observable>() {
        @Override
        public boolean filterObservable(Observable observable, List<Observable> chain) {
            return true;
        }
    };
}
