package de.chaosolymp.core.bungee.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.chaosolymp.core.bungee.BungeePlugin;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public final class PluginMessageListener implements Listener {

    private final transient BungeePlugin plugin;

    public PluginMessageListener(final BungeePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleReceive(final PluginMessageEvent event) {
        if(event.getTag().equalsIgnoreCase("BungeeCord") || event.getTag().equals("bungeecord:main")) {
            final ByteArrayDataInput input = ByteStreams.newDataInput(event.getData());
            final String subChannel = input.readUTF();
            if(subChannel.equalsIgnoreCase("core:server_msg")) {
                final String sender = input.readUTF();
                final String server = input.readUTF();
                final int length = input.readInt();
                byte[] data = new byte[length];
                input.readFully(data);
                if(this.plugin.getProxy().getServers().containsKey(server)) {
                    final ByteArrayDataOutput output = ByteStreams.newDataOutput();
                    output.writeUTF("core:server_msg");
                    output.writeUTF(sender);
                    output.writeInt(length);
                    output.write(data);
                    this.plugin.getProxy().getServerInfo(server).sendData("bungeecord:main", output.toByteArray());
                } else {
                    this.plugin.getLogger().warning(String.format("Got invalid server message request (Server %s does not exist).", server));
                }

            }
        }
    }

}
