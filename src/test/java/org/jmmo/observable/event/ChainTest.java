package org.jmmo.observable.event;

import org.jmmo.observable.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * User: Tomas
 * Date: 19.02.13
 * Time: 15:46
 */
public class ChainTest {
    private ObservableSupport observableBase;
    private ObservableContainerSupport observableContainer;

    @Before
    public void setUp() throws Exception {
        observableBase = new ObservableSupport(){
            @Override
            protected Observable getOwner() {
                return this;
            }
        };
        observableContainer = new ObservableContainerSupportCol() {
            @Override
            protected Observable getOwner() {
                return this;
            }
        };
        observableContainer.addChildObservable(observableBase);
    }

    //empty chain
    @Test
    public void testFirst1_0() throws Exception {
        final int[] flag = new int[] {0};
        observableContainer.addObservableListener(new ObservableAdapter() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                Chain.first(chain, ObservableContainerSupport.class, new Chain.Handler1<ObservableContainerSupport>() {
                    @Override
                    public void chain(ObservableContainerSupport observable1) {
                        Assert.assertThat(observable1, Matchers.sameInstance(observableContainer));
                        flag[0]++;
                    }
                });
            }
        });

        observableContainer.fireObservableEvent(new ObservableEvent(observableBase));
        Assert.assertEquals(0, flag[0]);
    }

    //appropriate class in chain
    @Test
    public void testFirst1_1() throws Exception {
        final int[] flag = new int[] {0};
        observableContainer.addObservableListener(new ObservableAdapter() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                Chain.first(chain, ObservableContainerSupport.class, new Chain.Handler1<ObservableContainerSupport>() {
                    @Override
                    public void chain(ObservableContainerSupport observable1) {
                        Assert.assertThat(observable1, Matchers.sameInstance(observableContainer));
                        flag[0]++;
                    }
                });
            }
        });

        observableBase.fireObservableEvent(new ObservableEvent(observableBase));
        Assert.assertEquals(1, flag[0]);
    }

    //not appropriate class in chain
    @Test
    public void testFirst1_2() throws Exception {
        final int[] flag = new int[] {0};
        observableContainer.addObservableListener(new ObservableAdapter() {
            @Override
            public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
                Chain.first(chain, ObservableTransparentContainerSupport.class, new Chain.Handler1<ObservableTransparentContainerSupport>() {
                    @Override
                    public void chain(ObservableTransparentContainerSupport observable1) {
                        Assert.assertThat(observable1, Matchers.sameInstance(observableContainer));
                        flag[0]++;
                    }
                });
            }
        });

        observableBase.fireObservableEvent(new ObservableEvent(observableBase));
        Assert.assertEquals(0, flag[0]);
    }

    @Test
    public void testFirst2() throws Exception {

    }
}
