package org.harrydev.discordx.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.harrydev.discordx.Utils.interfaces.Registerable;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, Registerable {
    private final String name;
    private final String permission;
    private final boolean playersOnly;

    protected AbstractCommand(String name, String permission, boolean playersOnly) {
        this.name = name;
        this.permission = permission;
        this.playersOnly = playersOnly;
    }

    public String getPermission() {
        return this.permission;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!command.getName().equalsIgnoreCase(name)) return false;

        if(playersOnly && !(sender instanceof Player)) {
            sender.sendMessage("This Command Can Only Be executed By a Player!");
            return true;
        }

        if (!sender.hasPermission(permission) && sender instanceof Player) {
            sender.sendMessage("You Dont Have Permission to Execute This Command");
            return true;
        }

        onExecute(sender, Arrays.asList(args));

        return true;
    }

    @Override
    public final void register() {
        Bukkit.getPluginCommand(name).setExecutor(this);
    }

    public abstract void onExecute(CommandSender sender, List<String> args);
}
