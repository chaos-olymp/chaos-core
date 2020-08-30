package de.chaosolymp.core.bungee.command;

import de.chaosolymp.core.bungee.BungeePlugin;
import java.awt.Color;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public final class UserCheckCommand extends Command {

    private final BungeePlugin plugin;

    public UserCheckCommand(BungeePlugin plugin) {
        super("user-check", "core.user-check", "check-user", "uc");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1) {
            final String targetName = args[0];
            ProxiedPlayer target = this.plugin.getProxy().getPlayer(targetName);

            if(target != null) {
                ComponentBuilder builder = new ComponentBuilder();

                builder.append("Name: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getName()).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("UUID: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getUniqueId().toString()).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("Server: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getServer().getInfo().getName()).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("Main Hand: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getMainHand().name()).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("Locale: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getLocale().getDisplayName()).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("Chat Mode: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getChatMode().name()).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("View Distance: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(Byte.toString(target.getViewDistance())).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("Ping: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(Integer.toString(target.getPing())).color(ChatColor.of(new Color(0, 206, 201)));

                builder.append("Host: ").color(ChatColor.of(new Color(26, 188, 156)));
                builder.append(target.getPendingConnection().getVirtualHost().getAddress().getHostAddress() + ":" + target.getPendingConnection().getVirtualHost().getPort()).color(ChatColor.of(new Color(0, 206, 201)));

                sender.sendMessage(builder.create());
            }
        }
    }
}
