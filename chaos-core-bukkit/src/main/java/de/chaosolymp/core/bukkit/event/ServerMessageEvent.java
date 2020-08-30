package de.chaosolymp.core.bukkit.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public final class ServerMessageEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String sender;
    private final byte[] message;

    public ServerMessageEvent(String sender, byte[] message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public String getSender() {
        return sender;
    }

    public byte[] getMessage() {
        return message;
    }
}
