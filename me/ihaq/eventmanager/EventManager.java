package me.ihaq.eventmanager;

import me.ihaq.eventmanager.data.EventData;
import me.ihaq.eventmanager.listener.EventTarget;
import me.ihaq.eventmanager.listener.Listener;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public enum EventManager {

    INSTANCE;

    //TODO -> Documentation

    private final Map<Class<? extends Event>, List<EventData>> REGISTRY_MAP = new HashMap<>();

    public void register(Listener listener) {
        Arrays.stream(listener.getClass().getDeclaredMethods())
                .filter(this::isMethodValid)
                .forEach(method -> register(method, listener));
        REGISTRY_MAP.values()
                .forEach(eventDataList -> eventDataList.sort(((o1, o2) -> (o1.getPriority().getValue() - o2.getPriority().getValue()))));
    }

    public void register(Listener... listeners) {
        Arrays.stream(listeners).forEach(this::register);
    }

    @SuppressWarnings("unchecked")
    private void register(Method method, Listener listener) {

        if (!method.isAccessible())
            method.setAccessible(true);

        Class<? extends Event> clazz = (Class<? extends Event>) method.getParameterTypes()[0];
        EventData eventData = new EventData(listener, method, method.getAnnotation(EventTarget.class).value());

        List<EventData> list = REGISTRY_MAP.getOrDefault(clazz, new CopyOnWriteArrayList<>());

        if (!list.contains(eventData))
            list.add(eventData);

        REGISTRY_MAP.put(clazz, list);
    }

    public void unregister(Listener listener) {
        REGISTRY_MAP.values()
                .forEach(eventDataList -> eventDataList.removeIf(field -> field.getListener() == listener));
        REGISTRY_MAP.entrySet()
                .removeIf(hashSetEntry -> hashSetEntry.getValue().isEmpty());
    }

    public void unregister(Listener... listeners) {
        Arrays.stream(listeners).forEach(this::unregister);
    }

    private boolean isMethodValid(Method method) {
        return method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class) && Event.class.isAssignableFrom(method.getParameterTypes()[0]);
    }

    public List<EventData> getEventsData(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

}