package de.chaosolymp.core.command

import de.chaosolymp.core.CorePlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GarbageCollectorCommand(private val plugin: CorePlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("core.gc")) {
            System.gc()
            if(sender is Player) this.plugin.logger.info("${sender.name} started the garbage collector") else this.plugin.logger.info("Running garbage collector.")
            // TODO: Garbage collector message
        } else {
            // TODO: No permission message
        }
        return true
    }

}
