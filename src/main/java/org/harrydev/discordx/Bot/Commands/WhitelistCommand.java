package org.harrydev.discordx.Bot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.harrydev.discordx.Utils.Lag;

import java.awt.*;

public class WhitelistCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("!whitelist")) {
            String[] username = e.getMessage().getContentRaw().split(" ");
            EmbedBuilder eb = new EmbedBuilder();
            String Avatar = "https://cravatar.eu/avatar/"+username[1]+"/256.png";
            eb.setColor(Color.GREEN);
            eb.setAuthor("Whitelisted " + username[1] + "!", null, Avatar);
            e.getChannel().sendMessage(eb.build()).queue();
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "whitelist " + username[1];
            Bukkit.dispatchCommand(console, command);
        }
    }

}