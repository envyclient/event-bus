import com.envyclient.eventbus.Event;
import com.envyclient.eventbus.EventBus;
import com.envyclient.eventbus.data.EventDirection;
import com.envyclient.eventbus.listener.EventListener;
import com.envyclient.eventbus.listener.EventTarget;
import com.envyclient.eventbus.type.Cancellable;
import com.envyclient.eventbus.type.Direction;

public class Main {

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        Test test = new Test();

        // registering
        eventBus.register(test);

        eventBus.callEvent(new TestEvent());
        eventBus.callEvent(new TestEventCancellable());
        eventBus.callEvent(new TestEventDirection());

        TestEventCancellableDirection testEventCancellableDirection = new TestEventCancellableDirection();
        testEventCancellableDirection.setCancelled(true);
        eventBus.callEvent(testEventCancellableDirection);

        // un-registering
        eventBus.unregister(test);

        eventBus.callEvent(new TestEvent());
    }

    // Every class you register must implement EventListener
    private static class Test implements EventListener {

        @EventTarget
        public void onEvent(TestEvent event) {
            System.out.println("TestEvent");
            System.out.println("test");
            System.out.println();
        }

        @EventTarget
        public void onEvent(TestEventCancellable event) {
            System.out.println("TestEventCancellable");
            System.out.println(event.isCancelled());
            System.out.println();
        }

        @EventTarget
        public void onEvent(TestEventDirection event) {
            System.out.println("TestEventDirection");
            System.out.println(event.getDirection());
            System.out.println();
        }

        @EventTarget
        public void onEvent(TestEventCancellableDirection event) {
            System.out.println("TestEventCancellableDirection");
            System.out.println(event.getDirection());
            System.out.println(event.isCancelled());
            System.out.println();
        }

    }

    private static class TestEvent extends Event {

    }

    private static class TestEventCancellable extends Event implements Cancellable {

        private boolean cancelled;

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }
    }

    private static class TestEventDirection extends Event implements Direction {

        @Override
        public EventDirection getDirection() {
            return EventDirection.IN;
        }
    }

    private static class TestEventCancellableDirection extends Event implements Cancellable, Direction {

        private boolean cancelled;

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }

        @Override
        public EventDirection getDirection() {
            return EventDirection.OUT;
        }
    }
}
