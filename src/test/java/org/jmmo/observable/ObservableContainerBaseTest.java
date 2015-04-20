package org.jmmo.observable;

import org.jmmo.observable.event.AddedObservableEvent;
import org.jmmo.observable.event.ObservableEvent;
import org.jmmo.observable.event.ObservableListener;
import org.jmmo.observable.event.RemovedObservableEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testing.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * User: Tomas
 * Date: 04.01.13
 * Time: 15:24
 */
@RunWith(Parameterized.class)
public class ObservableContainerBaseTest {

    public interface ObservableContainerBaseCreator extends ObservableBaseTest.ObservableBaseCreator {
        @Override
        ObservableContainerBase createObservable();
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{{ new ObservableContainerBaseCreator() {
            @Override
            public ObservableContainerBase createObservable() {
                return new ObservableContainerBaseCol() {
                    @Override
                    public String toString() {
                        return "ObservableContainerBaseCreator";
                    }
                };
            }
        } }});
    }

    private ObservableContainerBaseCreator observableContainerBaseCreator;
    private ObservableContainerBase observableContainerBase;

    public ObservableContainerBaseTest(ObservableContainerBaseCreator observableContainerBaseCreator) {
        this.observableContainerBaseCreator = observableContainerBaseCreator;
    }

    @Before
    public void setUp() throws Exception {
        observableContainerBase = observableContainerBaseCreator.createObservable();
    }

    @Test
    public void testAddChildObservable() throws Exception {
        Observable observable = new ObservableBase() {};
        ObservableListener listener = TestHelper.createMockListener();
        observableContainerBase.addObservableListener(listener);

        observableContainerBase.addChildObservable(observable);
        verify(listener).handleObservableEvent(new AddedObservableEvent(observableContainerBase, observable), Collections.<Observable>emptyList());
    }

    @Test
    public void testRemoveChildObservable() throws Exception {
        Observable observable = new ObservableBase() {};
        ObservableListener listener = TestHelper.createMockListener();
        observableContainerBase.addObservableListener(listener);

        observableContainerBase.removeChildObservable(observable);

        verify(listener, never()).handleObservableEvent(any(ObservableEvent.class), eq(Collections.<Observable>emptyList()));
        observableContainerBase.addChildObservable(observable);
        observableContainerBase.removeChildObservable(observable);
        verify(listener).handleObservableEvent(new RemovedObservableEvent(observableContainerBase, observable), Collections.<Observable>emptyList());
    }

    @Test
    public void testBubblingChildEventLevel1() throws Exception {
        ObservableBase observableBase = new ObservableBase() {};
        ObservableEvent event = new ObservableEvent(observableBase);
        ObservableListener listener = TestHelper.createMockListener();
        observableContainerBase.addObservableListener(listener);

        observableContainerBase.addChildObservable(observableBase);
        verify(listener).handleObservableEvent(new AddedObservableEvent(observableContainerBase, observableBase), Collections.<Observable>emptyList());
        observableBase.fireObservableEvent(event);
        verify(listener).handleObservableEvent(isA(ObservableEvent.class), eq(Collections.<Observable>singletonList(observableContainerBase)));
    }

    @Test
    public void testBubblingChildEventLevel2() throws Exception {
        ObservableBase observableBase = new ObservableBase() {};
        ObservableContainerBase observableContainerSlave = new ObservableContainerBaseCol() {
            @Override
            public String toString() {
                return "ObservableContainerSlave";
            }
        };

        observableContainerBase.addChildObservable(observableContainerSlave);
        ObservableListener listener = TestHelper.createMockListener();
        observableContainerBase.addObservableListener(listener);
        observableContainerSlave.addChildObservable(observableBase);
        verify(listener).handleObservableEvent(new AddedObservableEvent(observableContainerSlave, observableBase), Collections.<Observable>singletonList(observableContainerBase));

        ObservableEvent event = new ObservableEvent(observableBase);
        observableBase.fireObservableEvent(event);
        verify(listener).handleObservableEvent(isA(ObservableEvent.class), eq(Arrays.<Observable>asList(observableContainerBase, observableContainerSlave)));

        observableContainerBase.removeChildObservable(observableContainerSlave);
        verify(listener).handleObservableEvent(new RemovedObservableEvent(observableContainerBase, observableContainerSlave), Collections.<Observable>emptyList());

        reset(listener);
        observableBase.fireObservableEvent(event);
        verifyZeroInteractions(listener);
    }
}
