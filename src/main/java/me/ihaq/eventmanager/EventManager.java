package me.ihaq.eventmanager;

import me.ihaq.eventmanager.data.EventData;
import me.ihaq.eventmanager.listener.EventListener;
import me.ihaq.eventmanager.listener.EventTarget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {

    private final Map<Class<? extends Event>, List<EventData>> registryMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public synchronized void register(EventListener... eventListeners) {

        // looping through all the methods in the eventListeners and adding them to registryMap if they are valid
        for (EventListener eventListener : eventListeners) {
            for (Method method : eventListener.getClass().getDeclaredMethods()) {
                if (method.getParameterTypes().length == 1
                        && method.isAnnotationPresent(EventTarget.class)
                        && Event.class.isAssignableFrom(method.getParameterTypes()[0])) {

                    method.setAccessible(true);

                    Class<? extends Event> clazz = (Class<? extends Event>) method.getParameterTypes()[0];
                    EventData eventData = new EventData(eventListener, method, method.getAnnotation(EventTarget.class).value());

                    List<EventData> list = registryMap.getOrDefault(clazz, new CopyOnWriteArrayList<>());

                    if (!list.contains(eventData)) {
                        list.add(eventData);
                    }

                    registryMap.put(clazz, list);
                }
            }
        }

        // sorting the registry map
        for (List<EventData> eventDataList : registryMap.values()) {
            eventDataList.sort(Comparator.comparingInt(o -> o.getPriority().getValue()));
        }
    }

    public synchronized void unregister(EventListener... eventListeners) {

        for (List<EventData> eventDataList : registryMap.values()) {
            for (EventListener eventListener : eventListeners) {
                eventDataList.removeIf(field -> field.getEventListener() == eventListener);
            }
        }

        // cleaning up the registryMap by removing any empty lists
        registryMap.entrySet().removeIf(hashSetEntry -> hashSetEntry.getValue().isEmpty());
    }

    public synchronized void callEvent(Event event) {
        List<EventData> dataList = registryMap.get(event.getClass());

        if (dataList == null) {
            return;
        }

        for (EventData eventData : dataList) {
            try {
                eventData.getMethod().invoke(eventData.getEventListener(), event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}