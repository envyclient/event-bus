# EventManager

A simple java event manager.

## Sample Usage

```
    public static void main(String[] args) {

        EventManager.INSTANCE.register(new Object() {

            @EventTarget(priority = EventPriority.LOW)
            public void onEvent(TestEvent e) {
                System.out.println("LOW <=> " + e.getType());
            }

            @EventTarget(priority = EventPriority.HIGH)
            public void onEvent1(TestEvent e) {
                System.out.println("HIGH <=> " + e.getType());
            }

            @EventTarget(events = {TestEvent.class}, priority = EventPriority.MEDIUM)
            public void onEvent2(Event e) {
                if (e instanceof TestEvent)
                    System.out.println("MEDIUM <=> " + e.getType());
            }


        });

        new TestEvent(EventType.PRE).call();
        new TestEvent(EventType.POST).call();

    }
	
```

That will give you this result in console.

```
LOW <=> PRE
MEDIUM <=> PRE
HIGH <=> PRE
LOW <=> POST
MEDIUM <=> POST
HIGH <=> POST
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details