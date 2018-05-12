package me.ihaq.eventmanager.data;

import me.ihaq.eventmanager.listener.EventListener;

import java.lang.reflect.Method;
import java.util.Objects;

public class EventData {

    private EventListener eventListener;
    private Method method;
    private EventPriority priority;

    public EventData(EventListener eventListener, Method method, EventPriority priority) {
        this.eventListener = eventListener;
        this.method = method;
        this.priority = priority;
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public Method getMethod() {
        return method;
    }

    public EventPriority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventData eventData = (EventData) o;
        return Objects.equals(eventListener, eventData.eventListener) &&
                Objects.equals(method, eventData.method) &&
                priority == eventData.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventListener, method, priority);
    }
}