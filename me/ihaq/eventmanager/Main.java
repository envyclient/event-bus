package me.ihaq.eventmanager;

import me.ihaq.eventmanager.event.data.EventTarget;
import me.ihaq.eventmanager.events.TestEvent;

public class Main {

    public static void main(String[] args) {
        EventManager.INSTANCE.register(new Object() {


            @EventTarget
            private void event(EventManager e) {

            }


        });

        new TestEvent().call();
    }

}
