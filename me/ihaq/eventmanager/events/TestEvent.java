package me.ihaq.eventmanager.events;

import me.ihaq.eventmanager.event.Event;
import me.ihaq.eventmanager.event.data.EventType;

public class TestEvent extends Event {

    public TestEvent() {
        super(EventType.PRE);
    }

}
