package me.ihaq.eventmanager.event.type;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);

}
