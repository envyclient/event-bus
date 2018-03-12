package me.ihaq.eventmanager.events;

import me.ihaq.eventmanager.event.Event;
import me.ihaq.eventmanager.event.data.EventType;
import me.ihaq.eventmanager.event.type.Cancellable;

public class TestEvent extends Event implements Cancellable {

    private boolean cancelled;

    public TestEvent(EventType eventType) {
        super(eventType);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}