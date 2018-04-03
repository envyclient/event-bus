package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.Event;
import me.ihaq.eventmanager.event.data.EventData;
import me.ihaq.eventmanager.event.listener.EventTarget;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public enum EventManager {

    INSTANCE;

    //TODO -> Documentation

    private final Map<Class<? extends Event>, List<EventData>> REGISTRY_MAP = new HashMap<>();

    public void register(Object object) {
        Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(this::isMethodValid)
                .forEach(method -> register(method, object));
        REGISTRY_MAP.values()
                .forEach(eventDataList -> eventDataList.sort(((o1, o2) -> (o1.getPriority().getValue() - o2.getPriority().getValue()))));
    }

    public void register(Object... objects) {
        Arrays.stream(objects).forEach(this::register);
    }

    @SuppressWarnings("unchecked")
    private void register(Method method, Object object) {

        EventData eventData = new EventData(object, method, method.getAnnotation(EventTarget.class).value());
        Class<? extends Event> clazz = (Class<? extends Event>) method.getParameterTypes()[0];
        
        if (REGISTRY_MAP.containsKey(clazz)) {
            if (!REGISTRY_MAP.get(clazz).contains(eventData))
                REGISTRY_MAP.get(clazz).add(eventData);
        } else {
            REGISTRY_MAP.put(clazz, new CopyOnWriteArrayList<>(Collections.singletonList(eventData)));
        }

    }

    public void unregister(Object object) {
        REGISTRY_MAP.values()
                .forEach(eventDataList -> eventDataList.removeIf(field -> field.getSource() == object));
        REGISTRY_MAP.entrySet()
                .removeIf(hashSetEntry -> hashSetEntry.getValue().isEmpty());
    }

    public void unregister(Object... objects) {
        Arrays.stream(objects).forEach(this::unregister);
    }

    private boolean isMethodValid(Method method) {
        return method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class) && Event.class.isAssignableFrom(method.getParameterTypes()[0]);
    }

    public List<EventData> getEventsData(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

}