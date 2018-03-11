package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.Event;
import me.ihaq.eventmanager.event.data.EventData;
import me.ihaq.eventmanager.event.data.EventTarget;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public enum EventManager {

    INSTANCE;

    //TODO -> Documentation

    private HashMap<Class<? extends Event>, CopyOnWriteArrayList<EventData>> REGISTRY_MAP = new HashMap<>();

    public void register(Object o) {

        Arrays.stream(o.getClass().getDeclaredMethods()).forEach(method -> {
            if (isMethodValid(method))
                register(method, o);
        });

        REGISTRY_MAP.values().forEach(flexibleArray -> flexibleArray.sort(((o1, o2) -> (o1.getPriority().getValue() - o2.getPriority().getValue()))));
    }


    @SuppressWarnings("unchecked")
    private void register(Method method, Object object) {

        EventData eventData = new EventData(object, method, method.getAnnotation(EventTarget.class).priority());
        Class<? extends Event> eventClass = (Class<? extends Event>) method.getParameterTypes()[0];

        if (!eventData.getTarget().isAccessible())
            eventData.getTarget().setAccessible(true);

        if (REGISTRY_MAP.containsKey(eventClass)) {
            if (!REGISTRY_MAP.get(eventClass).contains(eventData))
                REGISTRY_MAP.get(eventClass).add(eventData);
        } else {
            REGISTRY_MAP.put(eventClass, new CopyOnWriteArrayList<>(Collections.singletonList(eventData)));
        }
    }


    public void unregister(Object o) {
        REGISTRY_MAP.values().forEach(flexibleArray -> flexibleArray.removeIf(methodData -> methodData.getSource().equals(o)));
        REGISTRY_MAP.entrySet().removeIf(hashSetEntry -> hashSetEntry.getValue().isEmpty());
    }

    private boolean isMethodValid(Method method) {
        return method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class) && Event.class.isAssignableFrom(method.getParameterTypes()[0]);
    }

    public CopyOnWriteArrayList<EventData> get(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

}