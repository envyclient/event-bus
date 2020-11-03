package com.envyclient.eventbus.type;

public interface Cancellable {

    boolean cancelled = false;

    boolean isCancelled();

    void setCancelled(boolean cancelled);

}