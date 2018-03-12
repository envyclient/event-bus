package me.ihaq.eventmanager.event.listener;

@FunctionalInterface
public interface EventHook<T> {
    void onEvent(T event);
}
