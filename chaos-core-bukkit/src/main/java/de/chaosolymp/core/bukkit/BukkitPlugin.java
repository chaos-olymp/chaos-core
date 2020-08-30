package de.chaosolymp.core.bukkit;

import de.chaosolymp.core.bukkit.listener.MessageListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final long startTime = System.currentTimeMillis();
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener(this));
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getLogger().info(String.format("Plugin warmup finished (Took %dms).", System.currentTimeMillis() - startTime));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
