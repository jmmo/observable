package testing;

import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.List;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: Tomas
 * Date: 18.11.12
 * Time: 19:00
 * Helper class for testing purpose.
 */
public class TestHelper {

    /**
     * Injects specified value to private static field of specified class.
     * @param clazz class which field will be injected
     * @param fieldName name of the injected field
     * @param value value for injection
     * @param <V> type of injected value
     * @throws NoSuchFieldException if field with specified fieldName was not found in specified class
     */
    public static <V> void injectPrivateStaticField(Class<?> clazz, String fieldName, V value) throws NoSuchFieldException {
        Field instance = clazz.getDeclaredField(fieldName);
        instance.setAccessible(true);
        try {
            instance.set(null, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        };
    }

    /**
     * Injects specified value to private field of specified object.
     * @param injectable object which field will be injected
     * @param fieldName name of the injected field
     * @param value value for injection
     * @param <V> type of injected value
     * @throws NoSuchFieldException if field with specified fieldName was not found in specified class
     */
    public static <V> void injectPrivateField(Object injectable, String fieldName, V value) throws NoSuchFieldException {
        Field instance = injectable.getClass().getDeclaredField(fieldName);
        instance.setAccessible(true);
        try {
            instance.set(injectable, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        };
    }

    public static Element xmlFromString(String xml) {
        return xmlFromInputSource(new InputSource(new StringReader(xml)));
    }

    public static Element xmlFromFile(String fileName) {
        return xmlFromInputSource(new InputSource(fileName));
    }

    public static Element xmlFromInputSource(InputSource inputSource) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        Document doc;
        try {
            doc = db.parse(inputSource);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return doc.getDocumentElement();
    }

    public static ObservableListener createMockListener() {
        ObservableListener listener = mock(ObservableListener.class);
        when(listener.filterObservable(notNull(Observable.class), notNull(List.class))).thenReturn(true);
        return listener;
    }
}
