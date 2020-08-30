package de.chaosolymp.core.bukkit.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import de.chaosolymp.core.bukkit.BukkitPlugin;
import de.chaosolymp.core.bukkit.event.ServerMessageEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public final class MessageListener implements PluginMessageListener {

    private final transient BukkitPlugin plugin;

    public MessageListener(final BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if(channel.equalsIgnoreCase("BungeeCord") || channel.equalsIgnoreCase("bungeecord:main")) {
            ByteArrayDataInput input = ByteStreams.newDataInput(message);
            final String subChannel = input.readUTF();
            if(subChannel.equalsIgnoreCase("core:server_msg")) {
                final String sender = input.readUTF();
                final int length = input.readInt();
                byte[] data = new byte[length];
                input.readFully(data);
                this.plugin.getServer().getPluginManager().callEvent(new ServerMessageEvent(sender, data));
            }
        }
    }
}
