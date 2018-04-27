[ ![Download](https://api.bintray.com/packages/ihaq/maven/event-manager/images/download.svg) ](https://bintray.com/ihaq/maven/event-manager/_latestVersion)
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
    public class TestEvent extends Event Type {

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
[ ![Download](https://api.bintray.com/packages/ihaq/maven/event-manager/images/download.svg) ](https://bintray.com/ihaq/maven/event-manager/_latestVersion)

Replace VERSION with the verion above.

#### Maven
```xml
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>

<dependency>
    <groupId>me.ihaq.eventmanager</groupId>
    <artifactId>event-manager</artifactId>
    <version>VERSION</version>
</dependency>
```

#### Gradle
```gradle
dependencies {
    compile 'me.ihaq.eventmanager:event-manager:VERSION'
}

repositories {
    jcenter()
}
```