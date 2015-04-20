package org.jmmo.observable.event;

import org.jmmo.observable.Observable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * User: Tomas
 * Date: 10.03.13
 * Time: 17:46
 */
public class ListenerBuilder {
    private final Map<Class<? extends ObservableEvent>, ObservableHandler<?>> classHandlerMap = new LinkedHashMap<Class<? extends ObservableEvent>, ObservableHandler<?>>();
    private final Map<Class<?>, ObservableFilter<?>> classFilterMap = new LinkedHashMap<Class<?>, ObservableFilter<?>>();

    @SuppressWarnings("unchecked")
    public <T extends ObservableEvent> ListenerBuilder addHandler(ObservableHandler<T> handler) {
        return addHandler((Class<T>) getTypeArgument(ObservableHandler.class, handler), handler);
    }

    public <T extends ObservableEvent> ListenerBuilder addHandler(Class<T> clazz, ObservableHandler<T> handler) {
        if (classHandlerMap.containsKey(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " already handled by " + classHandlerMap.get(clazz));
        }

        classHandlerMap.put(clazz, handler);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> ListenerBuilder addFilter(ObservableFilter<T> filter) {
        return addFilter((Class<T>) getTypeArgument(ObservableFilter.class, filter), filter);
    }

    public <T> ListenerBuilder addFilter(Class<T> clazz, ObservableFilter<T> filter) {
        if (classFilterMap.containsKey(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " already filtered by " + classFilterMap.get(clazz));
        }

        classFilterMap.put(clazz, filter);
        return this;
    }

    public ObservableListener build() {
        if (classHandlerMap.isEmpty()) {
            throw new IllegalArgumentException("Must be at least one handler to build listener");
        }

        final List<Class<? extends ObservableEvent>> handlerClasses = new ArrayList<Class<? extends ObservableEvent>>(classHandlerMap.keySet());
        final List<ObservableHandler<?>> handlers = new ArrayList<ObservableHandler<?>>(classHandlerMap.values());
        final List<Class<?>> filterClasses = new ArrayList<Class<?>>(classFilterMap.keySet());
        final List<ObservableFilter<?>> filters = new ArrayList<ObservableFilter<?>>(classFilterMap.values());

        ObservableHandler handler;
        if (handlers.size() == 1) {
            handler = new SingleObservableHandler(handlerClasses.get(0), handlers.get(0));
        }
        else {
            handler = new MultiObservableHandler(handlerClasses, handlers);
        }

        ObservableFilter filter;
        if (filters.isEmpty()) {
            filter = ObservableFilter.PassAll;
        }
        else if (classFilterMap.size() == 1) {
            filter = new SingleObservableFilter(filterClasses.get(0), filters.get(0));
        }
        else {
            filter = new MultiObservableFilter(filterClasses, filters);
        }

        return new ComplexObservableListener(handler, filter);
    }

    private static class SingleObservableHandler implements ObservableHandler<ObservableEvent> {
        private final Class<? extends ObservableEvent> clazz;
        private final ObservableHandler<?> handler;

        private SingleObservableHandler(Class<? extends ObservableEvent> clazz, ObservableHandler<?> handler) {
            this.clazz = clazz;
            this.handler = handler;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            if (clazz.isInstance(event)) {
                ((ObservableHandler) handler).handleObservableEvent(event, chain);
            }
        }
    }

    private static class MultiObservableHandler implements ObservableHandler<ObservableEvent> {
        private final List<Class<? extends ObservableEvent>> classes;
        private final List<ObservableHandler<?>> handlers;

        private MultiObservableHandler(List<Class<? extends ObservableEvent>> classes, List<ObservableHandler<?>> handlers) {
            this.classes = classes;
            this.handlers = handlers;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            for (int i = 0; i < handlers.size(); i++) {
                if (classes.get(i).isInstance(event)) {
                    ((ObservableHandler) handlers.get(i)).handleObservableEvent(event, chain);
                    break;
                }
            }
        }
    }

    private static class SingleObservableFilter implements ObservableFilter<Observable> {
        private final Class<?> clazz;
        private final ObservableFilter<?> filter;

        private SingleObservableFilter(Class<?> clazz, ObservableFilter<?> filter) {
            this.clazz = clazz;
            this.filter = filter;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean filterObservable(Observable observable, List<Observable> chain) {
            if (clazz.isInstance(observable)) {
                return ((ObservableFilter) filter).filterObservable(observable, chain);
            }
            return false;
        }
    }

    private static class MultiObservableFilter implements ObservableFilter<Observable> {
        private final List<Class<?>> classes;
        private final List<ObservableFilter<?>> filters;

        private MultiObservableFilter(List<Class<?>> classes, List<ObservableFilter<?>> filters) {
            this.classes = classes;
            this.filters = filters;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean filterObservable(Observable observable, List<Observable> chain) {
            for (int i = 0; i < filters.size(); i++) {
                if (classes.get(i).isInstance(observable)) {
                    return ((ObservableFilter) filters.get(i)).filterObservable(observable, chain);
                }
            }
            return false;
        }
    }

    private static class ComplexObservableListener implements ObservableListener {
        private final ObservableHandler handler;
        private final ObservableFilter filter;

        private ComplexObservableListener(ObservableHandler handler, ObservableFilter filter) {
            this.handler = handler;
            this.filter = filter;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean filterObservable(Object observable, List<Observable> chain) {
            return filter.filterObservable(observable, chain);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleObservableEvent(ObservableEvent event, List<Observable> chain) {
            handler.handleObservableEvent(event, chain);
        }
    }

    @SuppressWarnings("unchecked")
    protected static <R, T> Class<? extends R> getTypeArgument(Class<T> parameterClass, T generic) {
        return getTypeArgument(parameterClass, (Class<? extends T>) generic.getClass());
    }

    @SuppressWarnings("unchecked")
    protected static <R, T> Class<? extends R> getTypeArgument(Class<T> parameterClass, Class<? extends T> clazz) {
        for (Type type : clazz.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType ptype = (ParameterizedType) type;
                if (parameterClass.isAssignableFrom((Class<?>) ptype.getRawType())) {
                    Type itype = ptype.getActualTypeArguments()[0];
                    Class<? extends R> pclass;
                    if (itype instanceof ParameterizedType) {
                        pclass = (Class<? extends R>) ((ParameterizedType) itype).getRawType();
                    }
                    else {
                        pclass = (Class<? extends R>) itype;
                    }
                    return pclass;
                }
            }
        }

        throw new IllegalArgumentException(parameterClass + " class is not found in handler " + clazz);
    }

}
