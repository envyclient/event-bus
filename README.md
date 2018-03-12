# EventManager

A simple java event manager.

## Creating an event

```java
public class TestEvent extends Event implements Cancellable {

    private boolean cancelled;

    
    public TestEvent(EventType type) {
        super(type); // The event type can be SINGLE, PRE, and POST.
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
```

## Listening for an event
```java
public class Main {

    public static void main(String[] args) {

        EventManager.INSTANCE.register(new Object() {

            @EventTarget // Every method listing for the event must have @EventTarget annotation.
            public void onTestEvent(TestEvent e) { // Every method must only have 1 parameter, and that parameter has to be an instance of the Event class.
                System.out.println("test");
            }

        });
    }

}

```

## Firing an event
```java
public class Main {

    public static void main(String[] args) {
        TestEvent testEvent = new Test
    }

}

```