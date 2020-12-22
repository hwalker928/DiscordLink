package org.harrydev.discordx.Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.harrydev.discordx.DiscordX;

import java.awt.Color;

public class JoinLeaveEvent implements Listener {
    private static final DiscordX INSTANCE = DiscordX.getInstance();

    FileConfiguration config;
    JDA bot;
    TextChannel textChannel;

    public JoinLeaveEvent(JDA jda) {
        this.bot = jda;
        this.config = INSTANCE.getConfig();
        textChannel = jda.getTextChannelById(INSTANCE.getConfig().getLong("chatChannel"));
    }

    @EventHandler
    public void OnPlayerJoin(PlayerLoginEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        String Avatar = "https://cravatar.eu/avatar/"+event.getPlayer().getName()+"/256.png";
        eb.setColor(Color.GREEN);
        eb.setAuthor(event.getPlayer().getName() + " joined the server", null, Avatar);
        textChannel.sendMessage(eb.build()).queue();
    }

    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        String Avatar = "https://cravatar.eu/avatar/"+event.getPlayer().getName()+"/256.png";
        eb.setColor(Color.RED);
        eb.setAuthor(event.getPlayer().getName() + " left the server", null, Avatar);
        textChannel.sendMessage(eb.build()).queue();
    }
}
