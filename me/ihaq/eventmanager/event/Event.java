package me.ihaq.eventmanager.event;

import me.ihaq.eventmanager.EventManager;
import me.ihaq.eventmanager.event.data.EventType;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public abstract class Event {

    private EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    public void fire() {
        Objects.requireNonNull(EventManager.INSTANCE.getData(getClass())).forEach(eventData -> eventData.getListener().onEvent(this));
    }

    public EventType getType() {
        return type;
    }

}