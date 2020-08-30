package de.chaosolymp.core.bungee.util;

import de.chaosolymp.core.bungee.BungeePlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public final class BungeeExecutor implements Executor {

    private final BungeePlugin plugin;

    public BungeeExecutor(final BungeePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull Runnable command) {
        this.plugin.getProxy().getScheduler().runAsync(this.plugin, command);
    }
}
