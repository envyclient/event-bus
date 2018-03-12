package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.Event;
import me.ihaq.eventmanager.event.data.EventData;
import me.ihaq.eventmanager.event.listener.EventListener;
import me.ihaq.eventmanager.event.listener.EventTarget;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public enum EventManager {

    INSTANCE;

    //TODO -> Documentation

    private HashMap<Class<? extends Event>, CopyOnWriteArrayList<EventData>> REGISTRY_MAP = new HashMap<>();

    public void register(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields()).filter(this::isFieldValid).forEach(field -> register(object, field));
        REGISTRY_MAP.values().forEach(eventDataList -> eventDataList.sort(((o1, o2) -> (o1.getPriority().getValue() - o2.getPriority().getValue()))));
    }

    public void register(Object... objects) {
        Arrays.stream(objects).forEach(this::register);
    }

    @SuppressWarnings("unchecked")
    private void register(Object object, Field field) {
        try {

            field.setAccessible(true);

            EventListener eventListener = (EventListener) field.get(object);
            EventData eventData = new EventData(object, eventListener, field.getAnnotation(EventTarget.class).priority());
            Class<?> eventClass = null;
            ;

            System.out.println(eventListener.getClass().getTypeName());

            if (REGISTRY_MAP.containsKey(eventClass)) {
                if (!REGISTRY_MAP.get(eventClass).contains(eventData))
                    REGISTRY_MAP.get(eventClass).add(eventData);
            } else {
                REGISTRY_MAP.put(eventClass, new CopyOnWriteArrayList<>(Collections.singletonList(eventData)));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void unregister(Object object) {
        REGISTRY_MAP.values().forEach(eventDataList -> eventDataList.removeIf(field -> field.getSource() == object));
        REGISTRY_MAP.entrySet().removeIf(hashSetEntry -> hashSetEntry.getValue().isEmpty());
    }

    public void unregister(Object... objects) {
        Arrays.stream(objects).forEach(this::unregister);
    }

    private boolean isFieldValid(Field field) {
        return field.isAnnotationPresent(EventTarget.class) && EventListener.class.isAssignableFrom(field.getType());
    }

    public CopyOnWriteArrayList<EventData> getData(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

}