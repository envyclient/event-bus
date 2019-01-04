package me.ihaq.eventmanager.data;

public enum EventPriority {

    LOW((byte) 0),
    MEDIUM((byte) 1),
    HIGH((byte) 2);

    private byte value;

    EventPriority(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}