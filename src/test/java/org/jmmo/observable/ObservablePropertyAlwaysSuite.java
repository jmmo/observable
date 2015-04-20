package org.jmmo.observable;

import org.jmmo.testing.JUnitFactoryRunner;
import org.jmmo.testing.JUnitFactoryRunner.JUnitFactory;
import org.junit.runner.RunWith;


/**
 * User: Tomas
 * Date: 07.01.13
 * Time: 9:16
 */
@RunWith(JUnitFactoryRunner.class)
public class ObservablePropertyAlwaysSuite {

    @JUnitFactory
    public static ObservableBaseTest test1() {
        return new ObservableBaseTest(new ObservableBaseTest.ObservableBaseCreator() {
            @Override
            public ObservableBase createObservable() {
                return new ObservablePropertyAlways();
            }
        });
    }

    @JUnitFactory
    public static ObservablePropertyAlwaysTest test2() {
        return new ObservablePropertyAlwaysTest(new ObservablePropertyAlwaysTest.ObservablePropertyAlwaysCreator() {
            @Override
            public <T> ObservableProperty<T> createProperty(T initValue) {
                return new ObservablePropertyAlways<T>(initValue);
            }
        });
    }
}
