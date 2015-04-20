package org.jmmo.observable.event.handlers;

import org.jmmo.observable.Observable;
import org.jmmo.observable.event.ObservableAdapter;
import org.jmmo.observable.event.ObservableEvent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Tomas
 * Date: 15.02.13
 * Time: 18:53
 */
public abstract class EventHandler1<T1 extends ObservableEvent> extends ObservableAdapter {
    protected final List<Class<? extends ObservableEvent>> classes;

    @Override
    public final void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
        handleEvent(event, chain);
    }

    public EventHandler1() {
        this(1);
    }

    protected EventHandler1(int number) {
        this.classes = checkTypeArgumentsNumber(getTypeArguments(this.getClass()), number);
    }

    protected boolean handleEvent(ObservableEvent event, List<Observable> chain) {
        if (classes.get(0).isInstance(event)) {
            handle1((T1) event, chain);
            return true;
        }
        return false;
    }

    protected abstract void handle1(T1 event1, List<Observable> chain);

    protected List<Class<? extends ObservableEvent>> checkTypeArgumentsNumber(List<Class<? extends ObservableEvent>> classesList, int number) {
        if (classesList.size() != number) {
            throw new IllegalArgumentException("Number of type arguments must be " + number + " but really it is " + classesList.size());
        }
        return classesList;
    }

    protected static List<Class<? extends ObservableEvent>> getTypeArguments(Class<? extends EventHandler1> clazz) {
        List<Class<? extends ObservableEvent>> result = new ArrayList<Class<? extends ObservableEvent>>();
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            for (Type itype : ptype.getActualTypeArguments()) {
                Class<? extends ObservableEvent> pclass;
                if (itype instanceof ParameterizedType) {
                    pclass = (Class<? extends ObservableEvent>) ((ParameterizedType) itype).getRawType();
                }
                else {
                    pclass = (Class<? extends ObservableEvent>) itype;
                }
                result.add(pclass);
            }
        }

        return result;
    }
}
