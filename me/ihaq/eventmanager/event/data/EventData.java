package me.ihaq.eventmanager.event.data;

import java.lang.reflect.Method;

public class EventData {

    private Object source;
    private Method target;
    private EventPriority priority;

    public EventData(Object source, Method target, EventPriority priority) {
        this.source = source;
        this.target = target;
        this.priority = priority;

        if (!target.isAccessible())
            target.setAccessible(true);
    }

    public Object getSource() {
        return source;
    }

    public Method getTarget() {
        return target;
    }

    public EventPriority getPriority() {
        return priority;
    }
}