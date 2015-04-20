package org.jmmo.observable.event;

import org.jmmo.observable.Observable;
import org.jmmo.observable.ObservableSupport;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * User: Tomas
 * Date: 10.03.13
 * Time: 20:42
 */
public class ListenerBuilderTest {
    private ObservableSupport observable = new ObservableSupport() {
        @Override
        protected Observable getOwner() {
            return this;
        }
    };

    private MyObservableSupport observable1 = new MyObservableSupport() {
        @Override
        public int getA() {
            return 1;
        }

        @Override
        public int getB() {
            return 1;
        }
    };

    private MyObservableSupport observable2 = new MyObservableSupport() {
        @Override
        public int getA() {
            return 1;
        }

        @Override
        public int getB() {
            return 2;
        }
    };

    @Test
    public void testAddHandler_1() throws Exception {
        MyEvent event = new MyEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(new ListenerBuilder().addHandler(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }).build());
        observable.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    @Test
    public void testAddHandler_2() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(new ListenerBuilder().addHandler(new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }).build());
        observable.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    @Test
    public void testAddHandler_3() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        observable.addObservableListener(new ListenerBuilder().addHandler(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }).build());
        observable.fireObservableEvent(event);
        Assert.assertFalse(flag[0]);
    }

    @Test
    public void testAddHandler_4() throws Exception {
        MyEvent event = new MyEvent(observable);
        MyEvent1 event1 = new MyEvent1(observable);
        final boolean[] flag = new boolean[] {false};
        final boolean[] flag1 = new boolean[] {false};
        observable.addObservableListener(new ListenerBuilder().addHandler(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }).addHandler(new ObservableHandler<MyEvent1>() {
            @Override
            public void handleObservableEvent(MyEvent1 event, List<Observable> chain) {
                flag1[0] = true;
            }
        }).build());

        observable.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
        Assert.assertFalse(flag1[0]);

        flag[0] = false;
        flag1[0] = false;
        observable.fireObservableEvent(event1);
        Assert.assertFalse(flag[0]);
        Assert.assertTrue(flag1[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildFail_1() throws Exception {
        observable.addObservableListener(new ListenerBuilder().build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildFail_2() throws Exception {
        observable.addObservableListener(new ListenerBuilder().addHandler(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
            }
        }).addHandler(new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
            }
        }).build());
    }

    @Test
    public void testAddFilter_1() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        ObservableListener listener = new ListenerBuilder().addFilter(new ObservableFilter<Observable>() {
            @Override
            public boolean filterObservable(Observable observable, List<Observable> chain) {
                if (observable instanceof MyObservableSupport) {
                    return ((MyObservableSupport) observable).getB() == 2;
                }
                return false;
            }
        }).addHandler(new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }).build();

        observable1.addObservableListener(listener);
        observable1.fireObservableEvent(event);
        Assert.assertFalse(flag[0]);

        observable2.addObservableListener(listener);
        observable2.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    @Test
    public void testAddFilter_2() throws Exception {
        ObservableEvent event = new ObservableEvent(observable);
        final boolean[] flag = new boolean[] {false};
        ObservableListener listener = new ListenerBuilder().addFilter(new ObservableFilter<MyObservableSupport>() {
            @Override
            public boolean filterObservable(MyObservableSupport observable, List<Observable> chain) {
                return observable.getA() == 1 && observable.getB() == 2;
            }
        }).addFilter(new ObservableFilter<Observable>() {
            @Override
            public boolean filterObservable(Observable observable, List<Observable> chain) {
                return true;
            }
        }).addHandler(new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                flag[0] = true;
            }
        }).build();

        observable1.addObservableListener(listener);
        observable1.fireObservableEvent(event);
        Assert.assertFalse(flag[0]);

        observable2.addObservableListener(listener);
        observable2.fireObservableEvent(event);
        Assert.assertTrue(flag[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFilterFail() throws Exception {
        observable.addObservableListener(new ListenerBuilder().addFilter(new ObservableFilter<MyObservableSupport>() {
            @Override
            public boolean filterObservable(MyObservableSupport observable, List<Observable> chain) {
                return false;
            }
        }).addFilter(new ObservableFilter<MyObservableSupport>() {
            @Override
            public boolean filterObservable(MyObservableSupport observable, List<Observable> chain) {
                return false;
            }
        }).addHandler(new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            }
        }).build());
    }

    @Test
    public void testGetTypeArgument() throws Exception {
        Assert.assertEquals(MyEvent.class, ListenerBuilder.getTypeArgument(ObservableHandler.class, new ObservableHandler<MyEvent>() {
            @Override
            public void handleObservableEvent(MyEvent event, List<Observable> chain) {
            }
        }));

        Assert.assertEquals(ObservableEvent.class, ListenerBuilder.getTypeArgument(ObservableHandler.class, new ObservableHandler<ObservableEvent>() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            }
        }));

        Assert.assertEquals(MyObservableSupport.class, ListenerBuilder.getTypeArgument(ObservableFilter.class, new ObservableFilter<MyObservableSupport>() {
            @Override
            public boolean filterObservable(MyObservableSupport observable, List<Observable> chain) {
                return false;
            }
        }));

        Assert.assertEquals(ObservableSupport.class, ListenerBuilder.getTypeArgument(ObservableFilter.class, new ObservableFilter<ObservableSupport>() {
            @Override
            public boolean filterObservable(ObservableSupport observable, List<Observable> chain) {
                return false;
            }
        }));
    }

    private static class MyEvent extends ObservableEvent {
        public MyEvent(Observable source) {
            super(source);
        }
    }

    private static class MyEvent1 extends ObservableEvent {
        public MyEvent1(Observable source) {
            super(source);
        }
    }

    private abstract static class MyObservableSupport extends ObservableSupport {
        public abstract int getA();
        public abstract int getB();

        @Override
        protected Observable getOwner() {
            return this;
        }
    }
}
