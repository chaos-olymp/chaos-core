package de.chaosolymp.core.bukkit.command;

import de.chaosolymp.core.bukkit.BukkitPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class GarbageCollectorCommand implements CommandExecutor {

    private final BukkitPlugin plugin;

    public GarbageCollectorCommand(final BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("core.gc")) {
            System.gc();
            this.plugin.getLogger().info(String.format("%s has started the garbage collector.", sender.getName()));
            // TODO: send garbage collector done message
        } else {
            // TODO: send no permission message
        }
        return true;
    }
}
