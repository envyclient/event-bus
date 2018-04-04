package me.ihaq.eventmanager.listener;

import me.ihaq.eventmanager.data.EventPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTarget {
    EventPriority value() default EventPriority.MEDIUM;
}