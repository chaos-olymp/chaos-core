package de.chaosolymp.core.bungee;

import de.chaosolymp.core.bungee.listener.PluginMessageListener;
import de.chaosolymp.core.bungee.util.BungeeExecutor;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.ExecutorService;

public final class BungeePlugin extends Plugin {

    private final BungeeExecutor executor;

    public BungeePlugin() {
        // Only initialize non-server-dependent things here
        this.executor = new BungeeExecutor(this);
    }

    @Override
    public void onEnable() {
        final long startTime = System.currentTimeMillis();
        this.getProxy().getPluginManager().registerListener(this, new PluginMessageListener(this));
        this.getLogger().info(String.format("Plugin warmup finished (Took %dms).", System.currentTimeMillis() - startTime));
    }

    @Override
    public void onDisable() {

    }

    public BungeeExecutor getExecutor() {
        return executor;
    }
}
