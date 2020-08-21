package me.affanhaq.eventmanager.type;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);

}