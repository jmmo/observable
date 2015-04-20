package org.jmmo.observable;

import org.jmmo.observable.event.ChangedValueEvent;
import org.jmmo.observable.event.ObservableListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * User: Tomas
 * Date: 07.01.13
 * Time: 8:55
 */
@RunWith(Parameterized.class)
public class ObservablePropertyAlwaysTest {

    public interface ObservablePropertyAlwaysCreator {

        <T> ObservableProperty<T> createProperty(T initValue);
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{{new ObservablePropertyAlwaysCreator() {
            @Override
            public <T> ObservableProperty<T> createProperty(T initValue) {
                return new ObservablePropertyAlways<T>(initValue);
            }
            @Override
            public String toString() {
                return "ObservablePropertyAlwaysCreator";
            }
        }}});
    }

    private ObservablePropertyAlwaysCreator observablePropertyAlwaysCreator;
    private ObservableProperty<Object> observableProperty;

    public ObservablePropertyAlwaysTest(ObservablePropertyAlwaysCreator observablePropertyAlwaysCreator) {
        this.observablePropertyAlwaysCreator = observablePropertyAlwaysCreator;
    }

    @Before
    public void setUp() throws Exception {
        observableProperty = observablePropertyAlwaysCreator.createProperty(null);
    }

    @Test
    public void testSetValue() throws Exception {
        ObservableListener listener = mock(ObservableListener.class);
        when(listener.filterObservable(notNull(Observable.class), notNull(List.class))).thenReturn(true);
        observableProperty.addObservableListener(listener);
        Object object1 = new Object();
        Object object2 = new Object();

        observableProperty.setValue(object1);
        verify(listener).handleObservableEvent(new ChangedValueEvent<Object>(observableProperty, null, object1), Collections.<Observable>emptyList());

        observableProperty.setValue(object1);
        verify(listener).handleObservableEvent(new ChangedValueEvent<Object>(observableProperty, object1, object1), Collections.<Observable>emptyList());

        observableProperty.setValue(object2);
        verify(listener).handleObservableEvent(new ChangedValueEvent<Object>(observableProperty, object1, object2), Collections.<Observable>emptyList());

        observableProperty.setValue(null);
        verify(listener).handleObservableEvent(new ChangedValueEvent<Object>(observableProperty, object2, null), Collections.<Observable>emptyList());
    }
}
