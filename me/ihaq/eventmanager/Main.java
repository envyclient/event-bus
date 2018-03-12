package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.data.EventPriority;
import me.ihaq.eventmanager.event.data.EventType;
import me.ihaq.eventmanager.event.listener.EventListener;
import me.ihaq.eventmanager.event.listener.EventTarget;
import me.ihaq.eventmanager.events.TestEvent;

public class Main {


    public static void main(String[] args) {

        Object obj = new Object() {

            @EventTarget(priority = EventPriority.LOW)
            public EventListener<TestEvent> listener = new EventListener<>(event -> {
                System.out.println("test1");
            });

        };

        EventManager.INSTANCE.register(obj);

        TestEvent testEvent = new TestEvent(EventType.SINGLE);
        testEvent.fire();

    }

}
