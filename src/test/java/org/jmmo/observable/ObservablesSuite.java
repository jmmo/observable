package org.jmmo.observable;

import org.jmmo.observable.event.ChainTest;
import org.jmmo.observable.event.EventTest;
import org.jmmo.observable.event.ListenerBuilderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * User: Tomas
 * Date: 07.01.13
 * Time: 9:53
 * All tests in observable package
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ObservableBaseTest.class,
        ObservableContainerBaseSuite.class,
        ObservablePropertyBaseSuite.class,
        ObservablePropertyAlwaysSuite.class,
        ChainTest.class,
        EventTest.class,
        ListenerBuilderTest.class
})
public class ObservablesSuite {
}
