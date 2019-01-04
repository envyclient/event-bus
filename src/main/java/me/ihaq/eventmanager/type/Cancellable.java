package me.ihaq.eventmanager.type;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);

}