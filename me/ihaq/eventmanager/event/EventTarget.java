package me.ihaq.eventmanager.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTarget {

    Class<? extends Event>[] events() default {};

    EventPriority priority() default EventPriority.MEDIUM;

}