package me.ihaq.eventmanager.event.data;

import me.ihaq.eventmanager.event.listener.EventListener;

public class EventData {

    private Object source;
    private EventListener listener;
    private EventPriority priority;

    public EventData(Object source, EventListener listener, EventPriority priority) {
        this.source = source;
        this.listener = listener;
        this.priority = priority;
    }

    public Object getSource() {
        return source;
    }

    public EventListener getListener() {
        return listener;
    }

    public EventPriority getPriority() {
        return priority;
    }
}