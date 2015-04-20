package org.jmmo.observable;

import org.jmmo.observable.ObservableContainerBaseTest.ObservableContainerBaseCreator;
import org.jmmo.testing.JUnitFactoryRunner;
import org.jmmo.testing.JUnitFactoryRunner.JUnitFactory;
import org.junit.runner.RunWith;


/**
 * User: Tomas
 * Date: 04.01.13
 * Time: 15:31
 */
@RunWith(JUnitFactoryRunner.class)
public class ObservableContainerBaseSuite {

    private static ObservableContainerBaseCreator observableContainerBaseCreator;

    static {
        observableContainerBaseCreator = new ObservableContainerBaseCreator() {
            @Override
            public ObservableContainerBase createObservable() {
                return new ObservableContainerBaseCol() {};
            }
        };
    }

    @JUnitFactory
    public static ObservableBaseTest test1() {
        return new ObservableBaseTest(observableContainerBaseCreator);
    }

    @JUnitFactory
    public static ObservableContainerBaseTest test2() {
        return new ObservableContainerBaseTest(observableContainerBaseCreator);
    }
}
