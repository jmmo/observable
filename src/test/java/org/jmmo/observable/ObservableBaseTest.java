package org.jmmo.observable;

import org.jmmo.observable.event.ObservableAdapter;
import org.jmmo.observable.event.ObservableEvent;
import org.jmmo.observable.event.ObservableListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testing.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * User: Tomas
 * Date: 04.01.13
 * Time: 12:28
 */
@RunWith(Parameterized.class)
public class ObservableBaseTest {

    public interface ObservableBaseCreator {

        ObservableBase createObservable();
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{{ new ObservableBaseCreator() {
            @Override
            public ObservableBase createObservable() {
                return new ObservableBase() {
                    @Override
                    public String toString() {
                        return "ObservableBaseCreator";
                    }
                };
            }
        } }});
    }

    private ObservableBaseCreator observableBaseCreator;
    private ObservableBase observableBase;

    public ObservableBaseTest(ObservableBaseCreator observableBaseCreator) {
        this.observableBaseCreator = observableBaseCreator;
    }

    @Before
    public void setUp() throws Exception {
        observableBase = observableBaseCreator.createObservable();
    }

    @Test
    public void testAddObservableListener() throws Exception {
        ObservableListener listener1 = TestHelper.createMockListener();
        observableBase.addObservableListener(listener1);
        assertThat(observableBase.subscribedListeners, containsInAnyOrder(listener1));

        ObservableListener listener2 = TestHelper.createMockListener();
        when(listener2.filterObservable(notNull(Observable.class), notNull(List.class))).thenReturn(true);
        observableBase.addObservableListener(listener2);
        assertThat(observableBase.subscribedListeners, containsInAnyOrder(listener1, listener2));

        ObservableListener listener3 = TestHelper.createMockListener();
        when(listener3.filterObservable(notNull(Observable.class), notNull(List.class))).thenReturn(false);
        observableBase.addObservableListener(listener3);
        assertThat(observableBase.subscribedListeners, containsInAnyOrder(listener1, listener2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddObservableListenerTwice() throws Exception {
        ObservableListener listener = TestHelper.createMockListener();
        observableBase.addObservableListener(listener);
        observableBase.addObservableListener(listener);
    }

    @Test
    public void testRemoveObservableListener() throws Exception {
        ObservableListener listener1 = TestHelper.createMockListener();
        ObservableListener listener2 = TestHelper.createMockListener();
        observableBase.addObservableListener(listener1);
        observableBase.addObservableListener(listener2);
        assertThat(observableBase.subscribedListeners, containsInAnyOrder(listener1, listener2));

        observableBase.removeObservableListener(listener1);
        assertThat(observableBase.subscribedListeners, containsInAnyOrder(listener2));

        observableBase.removeObservableListener(listener2);
        assertTrue(observableBase.subscribedListeners == null || observableBase.subscribedListeners.isEmpty());
    }

    @Test
    public void testFireObservableEvent() throws Exception {
        ObservableEvent event = new ObservableEvent(observableBase);
        observableBase.fireObservableEvent(event);

        ObservableListener listener1 = TestHelper.createMockListener();
        ObservableListener listener2 = TestHelper.createMockListener();
        observableBase.addObservableListener(listener1);
        observableBase.addObservableListener(listener2);
        assertThat(observableBase.subscribedListeners, containsInAnyOrder(listener1, listener2));

        observableBase.fireObservableEvent(event);
        verify(listener1).handleObservableEvent(event, Collections.<Observable>emptyList());
        verify(listener2).handleObservableEvent(event, Collections.<Observable>emptyList());

        reset(listener1, listener2);
        observableBase.removeObservableListener(listener1);
        observableBase.fireObservableEvent(event);
        verify(listener2).handleObservableEvent(event, Collections.<Observable>emptyList());
        verifyZeroInteractions(listener1);

        reset(listener1, listener2);
        observableBase.removeObservableListener(listener2);
        observableBase.fireObservableEvent(event);
        verifyZeroInteractions(listener1, listener2);
    }

    @Test
    public void testSubscribeInsideEvent() throws Exception {
        ObservableEvent event = new ObservableEvent(observableBase);
        final ObservableListener listener1 = TestHelper.createMockListener();
        final boolean[] flag = new boolean[] {false};
        ObservableListener listener2 = new ObservableAdapter() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                observableBase.removeObservableListener(this);
                observableBase.addObservableListener(listener1);
                flag[0] = true;
            }
        };
        observableBase.addObservableListener(listener2);

        observableBase.fireObservableEvent(event);
        assertTrue(flag[0]);
        verify(listener1, never()).handleObservableEvent(event, Collections.<Observable>emptyList());

        flag[0] = false;
        observableBase.fireObservableEvent(event);
        assertFalse(flag[0]);
        verify(listener1).handleObservableEvent(event, Collections.<Observable>emptyList());
    }
}