package de.chaosolymp.core

import de.chaosolymp.core.command.GarbageCollectorCommand
import org.bukkit.plugin.java.JavaPlugin

class CorePlugin: JavaPlugin() {

    override fun onEnable() {
        this.getCommand("gc")?.setExecutor(GarbageCollectorCommand(this))
    }
}