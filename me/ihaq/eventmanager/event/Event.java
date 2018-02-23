package me.ihaq.eventmanager.event;

import me.ihaq.eventmanager.EventManager;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Event {

    private boolean cancelled;
    private EventType type;

    public Event(EventType type) {
        this.type = type;
        this.cancelled = false;
    }

    public void call() {

        CopyOnWriteArrayList<EventData> dataList = EventManager.INSTANCE.get(getClass());

        if (dataList == null)
            return;

        dataList.forEach(data -> {

            try {
                data.getTarget().invoke(data.getSource(), this);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        });

    }

    public EventType getType() {
        return type;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}