package me.ihaq.eventmanager.event;

import me.ihaq.eventmanager.EventManager;
import me.ihaq.eventmanager.event.data.EventData;
import me.ihaq.eventmanager.event.data.EventType;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Event {

    private EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public void fire() {

        CopyOnWriteArrayList<EventData> eventDatalist = EventManager.INSTANCE.getData(getClass());

        if (eventDatalist == null)
            return;

        eventDatalist.forEach(eventData -> eventData.getListener().onEvent(this));
    }

    public EventType getType() {
        return type;
    }

}