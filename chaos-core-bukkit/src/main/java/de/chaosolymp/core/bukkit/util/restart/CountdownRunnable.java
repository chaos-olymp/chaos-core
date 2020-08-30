package de.chaosolymp.core.bukkit.util.restart;

import de.chaosolymp.core.bukkit.BukkitPlugin;

final class CountdownRunnable implements Runnable {

    private final BukkitPlugin plugin;
    private final Runnable restart;

    private int secondsLeft;

    public CountdownRunnable(BukkitPlugin plugin, int secondsLeft, Runnable restart) {
        this.plugin = plugin;
        this.secondsLeft = secondsLeft;
        this.restart = restart;
    }

    @Override
    public void run() {
        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {
            if(secondsLeft == 0) {
                this.plugin.getServer().getScheduler().runTask(this.plugin, this.restart);
            } else {
                this.plugin.getServer().getOnlinePlayers().forEach(player -> player.sendMessage("Server restarts in xy seconds" /* TODO configurable message */));
                this.secondsLeft--;
            }
        }, 0, 20L);
    }
}
