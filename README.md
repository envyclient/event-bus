[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)

# EventManager

A simple Java event manager.

## Creating an event

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

## Listening for an event
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

## Calling an event
```java
public class Main {

    public static void main(String[] args) {
       new EventManager().callEvent(new TestEvent())
    }

    // Example event
    public class TestEvent extends Event  {
    }

}
```

## Download

#### Maven
```xml
<repository>
    <id>ihaq-me</id>
    <name>ihaq-maven</name>
    <url>http://maven.ihaq.me/repo/</url>
</repository>

<dependency>
    <groupId>me.ihaq</groupId>
    <artifactId>event-manager</artifactId>
    <version>1.0</version>
</dependency>
```

#### Gradle
```gradle
repositories {
    maven {
        url "http://maven.ihaq.me/repo/"
    }
}

dependencies {
    compile 'me.ihaq:event-manager:1.0'
}
```