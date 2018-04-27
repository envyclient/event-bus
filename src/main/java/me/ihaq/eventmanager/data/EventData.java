package me.ihaq.eventmanager.data;

import me.ihaq.eventmanager.listener.Listener;

import java.lang.reflect.Method;
import java.util.Objects;

public class EventData {

    private Listener listener;
    private Method method;
    private EventPriority priority;

    public EventData(Listener listener, Method method, EventPriority priority) {
        this.listener = listener;
        this.method = method;
        this.priority = priority;
    }

    public Listener getListener() {
        return listener;
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
        return Objects.equals(listener, eventData.listener) &&
                Objects.equals(method, eventData.method) &&
                priority == eventData.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(listener, method, priority);
    }
}