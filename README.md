[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)
[![](https://jitpack.io/v/haq/event-manager.svg)](https://jitpack.io/#haq/event-manager)

# event-manager
A simple Java event manager.

## Creating

```java
    // Normal event
    public class TestEvent extends Event  {
    }
```

```java
    // Cancellable event
    public class TestEvent extends Event implements Cancellable {

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
```

```java
    // Type event, can be PRE or POST
    public class TestEvent extends Event implements Type {

        private EventType type;

        public TestEvent(EventType type) {
            this.type = type;
        }

        @Override
        public EventType getType() {
            return type;
        }
    }
```

## Listening
```java
public class Main {

    public static void main(String[] args) {

        EventManager eventManager = new EventManager();

        Test test = new Test();

        // registering
        eventManager.register(test);

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

    // Example event
    public class TestEvent extends Event  {
    }

}
```

## Calling
```java
public class Main {

    public static void main(String[] args) {
       new EventManager().callEvent(new TestEvent());
    }

    // Example event
    public class TestEvent extends Event  {
    }

}
```
