package org.jmmo.observable;

import org.jmmo.observable.event.ObservableListener;

import java.util.List;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockListenerHelper {

    public static ObservableListener createMockListener() {
        ObservableListener listener = mock(ObservableListener.class);
        when(listener.filterObservable(notNull(Observable.class), notNull(List.class))).thenReturn(true);
        return listener;
    }
}
