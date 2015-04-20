package org.jmmo.observable.event;

import org.jmmo.observable.Observable;
import org.jmmo.observable.ObservableSupport;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * User: Tomas
 * Date: 15.02.13
 * Time: 19:09
 */
public class EventTest {
    private ObservableSupport observable = new ObservableSupport() {
        @Override
        protected Observable getOwner() {
            return this;
        }
    };

    //check if fires appropriate event
    @Test
    public void testListener1_1() throws Exception {
        MyEvent event = new MyEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(Event.listener(MyEvent.class, ObservableEvent.class, new Event.Handler2<MyEvent, ObservableEvent>() {
            @Override
            public void event1(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }

            @Override
            public void event2(ObservableEvent event, List<Observable> chain) {
            }
        }));
        observable.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    //check if fires not appropriate event
    @Test
    public void testListener1_2() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(Event.listener(MyEvent.class, new Event.Handler1<MyEvent>() {
            @Override
            public void event1(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }));
        observable.fireObservableEvent(event);
        Assert.assertFalse(flag[0]);
    }

    @Test
    public void testGetEventClass() throws Exception {
        Assert.assertEquals(MyEvent.class, Event.getEventClass(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
            }
        }.getClass()));

        Assert.assertEquals(ObservableEvent.class, Event.getEventClass(new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            }
        }.getClass()));
    }

    @Test
    public void testListener_1() throws Exception {
        MyEvent event = new MyEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(Event.listener(ObservableFilter.PassAll, new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }));
        observable.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    @Test
    public void testListener_2() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(Event.listener(new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }));
        observable.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    @Test
    public void testListener_3() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(Event.listener(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }));
        observable.fireObservableEvent(event);
        Assert.assertFalse(flag[0]);
    }

    private static class MyEvent extends ObservableEvent {
        public MyEvent(Observable source) {
            super(source);
        }
    }
}
