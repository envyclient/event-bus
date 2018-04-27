import me.ihaq.eventmanager.Event;
import me.ihaq.eventmanager.EventManager;
import me.ihaq.eventmanager.listener.EventTarget;
import me.ihaq.eventmanager.listener.Listener;

public class Main {

    public static void main(String[] args) {

        EventManager eventManager = new EventManager();

        Test test = new Test();

        // registering
        eventManager.register(test);

        eventManager.callEvent(new TestEvent());

        // un-registering
        eventManager.unregister(test);
    }

    // Every class you register must implement Listener
    private static class Test implements Listener {

        @EventTarget
        public void onEvent(TestEvent event) {
            System.out.println("test");
        }

    }

    private static class TestEvent extends Event {

    }
}
