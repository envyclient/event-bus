package me.ihaq.eventmanager.event.listener;

import me.ihaq.eventmanager.event.Event;

@FunctionalInterface
public interface EventHook<T extends Event> {
    void onEvent(T event);
}
