package de.chaosolymp.core.bukkit.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import de.chaosolymp.core.bukkit.BukkitPlugin;
import de.chaosolymp.core.bukkit.event.ServerMessageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class ServerMessageListener implements Listener {

    private final BukkitPlugin plugin;

    public ServerMessageListener(BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleServerMessage(final ServerMessageEvent event) {
        ByteArrayDataInput input = ByteStreams.newDataInput(event.getMessage());
        final String sub = input.readUTF();
        if(sub.equalsIgnoreCase("core:shutdown")) {
            this.plugin.getLogger().info(String.format("Server %s requested shutdown.", event.getSender()));
            this.plugin.getServer().shutdown();
        }
    }

}
