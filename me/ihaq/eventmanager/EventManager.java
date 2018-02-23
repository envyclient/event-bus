package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.Event;
import me.ihaq.eventmanager.event.EventData;
import me.ihaq.eventmanager.event.EventTarget;

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
            if (method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class))
                register(method, o);
        });

        REGISTRY_MAP.values().forEach(flexibleArray -> flexibleArray.sort(((o1, o2) -> (o1.getPriority().getValue() - o2.getPriority().getValue()))));
    }


    @SuppressWarnings("unchecked")
    private void register(Method method, Object object) {


        if (method.getAnnotation(EventTarget.class).events().length > 0) {

            Class<? extends Event>[] classes = method.getAnnotation(EventTarget.class).events();

            Arrays.stream(classes).forEach(clazz -> registerEvent(new EventData(object, method, method.getAnnotation(EventTarget.class).priority()), clazz));

        } else {
            registerEvent(new EventData(object, method, method.getAnnotation(EventTarget.class).priority()), (Class<? extends Event>) method.getParameterTypes()[0]);
        }

    }


    public void unRegister(Object o) {
        REGISTRY_MAP.values().forEach(flexibleArray -> flexibleArray.removeIf(methodData -> methodData.getSource().equals(o)));
        REGISTRY_MAP.entrySet().removeIf(hashSetEntry -> hashSetEntry.getValue().isEmpty());
    }

    private void registerEvent(EventData eventData, Class<? extends Event> clazz) {
        if (!eventData.getTarget().isAccessible())
            eventData.getTarget().setAccessible(true);

        if (REGISTRY_MAP.containsKey(clazz)) {
            if (!REGISTRY_MAP.get(clazz).contains(eventData))
                REGISTRY_MAP.get(clazz).add(eventData);
        } else {
            REGISTRY_MAP.put(clazz, new CopyOnWriteArrayList<>(Collections.singletonList(eventData)));
        }
    }

    public CopyOnWriteArrayList<EventData> get(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

}