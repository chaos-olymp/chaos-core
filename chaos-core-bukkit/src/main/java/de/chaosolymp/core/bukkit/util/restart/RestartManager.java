package de.chaosolymp.core.bukkit.util.restart;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.chaosolymp.core.bukkit.BukkitPlugin;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class RestartManager {

    private final BukkitPlugin plugin;

    public RestartManager(BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    public void restartNow(String fallback) {
        final ByteArrayDataOutput output = ByteStreams.newDataOutput(7 + fallback.length());
        output.writeUTF("Connect");
        output.writeUTF(fallback);
        final byte[] data = output.toByteArray();
        this.plugin.getServer().getOnlinePlayers().forEach((player -> {
            player.sendMessage("Moving to fallback due restart"); // TODO: Implement configurable message
            player.sendPluginMessage(this.plugin, "BungeeCord", data);
        }));
        this.plugin.getServer().spigot().restart();
    }

    public void restartAfterDelay(final int seconds, String fallback) {
        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(this.plugin, new CountdownRunnable(this.plugin, 10, () -> restartNow(fallback)), (seconds - 10) * 20);
    }

    public void restartOnce(Instant at, String fallback) {
        final Instant now = Instant.now();
        final Duration duration = Duration.between(now, at);
        this.restartAfterDelay((int) duration.get(ChronoUnit.SECONDS), fallback);
    }
}
