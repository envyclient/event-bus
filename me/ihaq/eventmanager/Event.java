package me.ihaq.eventmanager;

import me.ihaq.eventmanager.data.EventData;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Event {

    public void call() {

        List<EventData> dataList = EventManager.INSTANCE.getEventsData(getClass());

        if (dataList == null)
            return;

        dataList.forEach(data -> {
            try {
                data.getMethod().invoke(data.getListener(), this);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

}