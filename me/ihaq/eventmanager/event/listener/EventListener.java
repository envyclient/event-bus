package me.ihaq.eventmanager.event.listener;

import me.ihaq.eventmanager.event.Event;

public final class EventListener<T extends Event> implements EventHook<T> {

    private EventHook<T> eventHook;
    private Class<T> target;

    @SuppressWarnings("unchecked")
    public EventListener(EventHook<T> eventHook) {
        this.eventHook = eventHook;
    }

    @Override
    public void onEvent(T event) {
        eventHook.onEvent(event);
    }

    public Class<T> getTarget() {
        return target;
    }
}