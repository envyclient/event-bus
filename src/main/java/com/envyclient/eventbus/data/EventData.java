package com.envyclient.eventbus.data;

import com.envyclient.eventbus.listener.EventListener;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;

public final class EventData {

    private final EventListener eventListener;
    private final Method method;
    private final EventPriority priority;

    public EventData(@NotNull EventListener eventListener, @NotNull Method method, @NotNull EventPriority priority) {
        this.eventListener = eventListener;
        this.method = method;
        this.priority = priority;
    }

    @NotNull
    public EventListener getEventListener() {
        return eventListener;
    }

    @NotNull
    public Method getMethod() {
        return method;
    }

    @NotNull
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