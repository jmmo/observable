package org.jmmo.observable.event.handlers;

import org.jmmo.observable.Observable;
import org.jmmo.observable.ObservableSupport;
import org.jmmo.observable.event.ObservableEvent;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * User: Tomas
 * Date: 15.02.13
 * Time: 19:09
 */
public class EventHandler1Test {
    private ObservableSupport observableBase;

    @Before
    public void setUp() throws Exception {
        observableBase = new ObservableSupport() {
            @Override
            protected Observable getOwner() {
                return this;
            }
        };
    }

    //check if fires appropriate event
    @Test
    public void testHandle1() throws Exception {
        MyEvent event = new MyEvent(observableBase);
        final boolean[] flag = new boolean[] {false};
        observableBase.addObservableListener(new EventHandler1<MyEvent>() {
            @Override
            protected void handle1(MyEvent event1, List<Observable> chain) {
                flag[0] = true;
            }
        });
        observableBase.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    //check if fires not appropriate event
    @Test
    public void testHandle2() throws Exception {
        ObservableEvent event = new ObservableEvent(observableBase);
        final boolean[] flag = new boolean[] {false};
        observableBase.addObservableListener(new EventHandler1<MyEvent>() {
            @Override
            protected void handle1(MyEvent event1, List<Observable> chain) {
                flag[0] = true;
            }
        });
        observableBase.fireObservableEvent(event);
        Assert.assertFalse(flag[0]);
    }

    private static class MyEvent extends ObservableEvent {
        public MyEvent(Observable source) {
            super(source);
        }
    }

    @Test
    public void testGetParameterType() throws Exception {
        EventHandler1<MyEvent> handler1 = new EventHandler1<MyEvent>() {
            @Override
            public void handle1(MyEvent event1, List<Observable> chain) {}
        };
        EventHandler2<MyEvent, ObservableEvent> handler2 = new EventHandler2<MyEvent, ObservableEvent>() {
            @Override
            protected void handle1(MyEvent event1, List<Observable> chain) {}
            @Override
            protected void handle2(ObservableEvent event2, List<Observable> chain) {}
        };

        Assert.assertThat(EventHandler1.getTypeArguments(handler2.getClass()), Matchers.contains(MyEvent.class, ObservableEvent.class));
    }
}
