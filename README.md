[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)
[![](https://jitpack.io/v/envyclient/event-bus.svg)](https://jitpack.io/#envyclient/event-bus)

# event-bus
The event system used in Envy Client.

## Creating

#### Basic
```java
// Normal event
public class TestEvent extends Event  {
}
```

#### Cancellable
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
}
```

#### Type
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

#### Direction
```java
// Type event, can be PRE or POST
public class TestEvent extends Event implements Direction {

    private EventDirection direction;

    public EventDirection(EventDirection direction) {
        this.direction = direction;
    }

    @Override
    public EventDirection getDirection() {
        return direction;
    }
}
```

## Listening & Calling
```java
public class Main {

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();
        Test test = new Test();

        // registering
        eventManager.register(test);

        eventBus.callEvent(new TestEvent());
            
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

## Download
#### Repository
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Dependency
```xml
<dependency>
    <groupId>com.github.envyclient</groupId>
    <artifactId>event-bus</artifactId>
    <version>VERSION_NUMBER</version>
</dependency>
```