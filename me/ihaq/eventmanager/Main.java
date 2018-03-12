package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.listener.EventListener;
import me.ihaq.eventmanager.events.TestEvent;

public class Main {


    public static void main(String[] args) {

        EventManager.INSTANCE.register(new Object() {

            public EventListener<TestEvent> testEventEventListener = new EventListener<TestEvent>() {

                @Override
                public void onEvent(Object e) {

                }

            };
        });

        TestEvent testEvent = new TestEvent();
        testEvent.fire();
    }

}
