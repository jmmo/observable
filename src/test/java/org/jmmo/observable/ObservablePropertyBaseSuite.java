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
public class ObservablePropertyBaseSuite {

    @JUnitFactory
    public static ObservableBaseTest test1() {
        return new ObservableBaseTest(new ObservableBaseTest.ObservableBaseCreator() {
            @Override
            public ObservableBase createObservable() {
                return new ObservablePropertyImpl();
            }
        });
    }

    @JUnitFactory
    public static ObservablePropertyBaseTest test2() {
        return new ObservablePropertyBaseTest(new ObservablePropertyBaseTest.ObservablePropertyBaseCreator() {
            @Override
            public <T> ObservableProperty<T> createProperty(T initValue) {
                return new ObservablePropertyImpl<T>(initValue);
            }
        });
    }
}
