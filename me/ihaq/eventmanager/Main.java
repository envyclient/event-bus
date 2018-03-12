package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.listener.EventListener;
import me.ihaq.eventmanager.event.listener.EventTarget;
import me.ihaq.eventmanager.events.TestEvent;

public class Main {


    public static void main(String[] args) {

        EventManager.INSTANCE.register(new Object() {

            @EventTarget
            public EventListener<TestEvent> listener = new EventListener<>(event -> {

            });
            
        });

        TestEvent testEvent = new TestEvent();
        //testEvent.fire();
    }

}
