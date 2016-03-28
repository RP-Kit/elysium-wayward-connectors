package com.seventh_root.elysium.chat.wayward.chatchannel;

import com.seventh_root.elysium.chat.bukkit.chatchannel.BukkitChatChannel;
import com.seventh_root.elysium.chat.wayward.ElysiumChatWayward;
import com.seventh_root.elysium.core.bukkit.util.ChatColorUtils;
import com.seventh_root.elysium.players.bukkit.BukkitPlayer;
import net.wayward_realms.waywardlib.chat.Channel;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class ChatChannelWrapper implements Channel {

    private final ElysiumChatWayward plugin;
    private final BukkitChatChannel elysiumChatChannel;

    public ChatChannelWrapper(ElysiumChatWayward plugin, BukkitChatChannel elysiumChatChannel) {
        this.plugin = plugin;
        this.elysiumChatChannel = elysiumChatChannel;
    }

    public BukkitChatChannel getElysiumChatChannel() {
        return elysiumChatChannel;
    }

    @Override
    public String getName() {
        return elysiumChatChannel.getName();
    }

    @Override
    public void setName(String name) {
        elysiumChatChannel.setName(name);
    }

    @Override
    public ChatColor getColour() {
        return ChatColorUtils.closestChatColorToColor(elysiumChatChannel.getColor());
    }

    @Override
    public void setColour(ChatColor colour) {
        elysiumChatChannel.setColor(ChatColorUtils.colorFromChatColor(colour));
    }

    @Override
    public String getFormat() {
        return elysiumChatChannel.getFormatString();
    }

    @Override
    public void setFormat(String format) {
        elysiumChatChannel.setFormatString(format);
    }

    @Override
    public int getRadius() {
        return elysiumChatChannel.getRadius();
    }

    @Override
    public void setRadius(int radius) {
        elysiumChatChannel.setRadius(radius);
    }

    @Override
    public Collection<Player> getSpeakers() {
        return elysiumChatChannel.getSpeakers()
                .stream()
                .filter(elysiumPlayer -> elysiumPlayer instanceof BukkitPlayer)
                .map(elysiumPlayer -> ((BukkitPlayer) elysiumPlayer).getBukkitPlayer())
                .filter(OfflinePlayer::isOnline)
                .map(OfflinePlayer::getPlayer)
                .collect(Collectors.toList());
    }

    @Override
    public void addSpeaker(Player speaker) {
        BukkitPlayer elysiumPlayer = plugin.getElysiumPlayerProvider().getPlayer(speaker);
        elysiumChatChannel.addSpeaker(elysiumPlayer);
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public void removeSpeaker(Player speaker) {
        BukkitPlayer elysiumPlayer = plugin.getElysiumPlayerProvider().getPlayer(speaker);
        elysiumChatChannel.removeSpeaker(elysiumPlayer);
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public Collection<Player> getListeners() {
        return elysiumChatChannel.getListeners()
                .stream()
                .filter(elysiumPlayer -> elysiumPlayer instanceof BukkitPlayer)
                .map(elysiumPlayer -> ((BukkitPlayer) elysiumPlayer).getBukkitPlayer())
                .filter(OfflinePlayer::isOnline)
                .map(OfflinePlayer::getPlayer)
                .collect(Collectors.toList());
    }

    @Override
    public void addListener(Player listener) {
        BukkitPlayer elysiumPlayer = plugin.getElysiumPlayerProvider().getPlayer(listener);
        elysiumChatChannel.addListener(elysiumPlayer);
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public void removeListener(Player listener) {
        BukkitPlayer elysiumPlayer = plugin.getElysiumPlayerProvider().getPlayer(listener);
        elysiumChatChannel.removeListener(elysiumPlayer);
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public boolean isGarbleEnabled() {
        return elysiumChatChannel.getClearRadius() > 0;
    }

    @Override
    public void setGarbleEnabled(boolean enable) {
        elysiumChatChannel.setClearRadius((int) Math.round((elysiumChatChannel.getRadius() * 0.75D)));
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public boolean isIrcEnabled() {
        return elysiumChatChannel.isIRCEnabled();
    }

    @Override
    public void setIrcEnabled(boolean enable) {
        elysiumChatChannel.setIRCEnabled(enable);
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public String getIrcChannel() {
        return elysiumChatChannel.getIRCChannel();
    }

    @Override
    public void setIrcChannel(String channel) {
        elysiumChatChannel.setIRCChannel(channel);
        plugin.getElysiumChatChannelProvider().updateChatChannel(elysiumChatChannel);
    }

    @Override
    public Command getCommand() {
        return null;
    }

    @Override
    public void setCommand(Command command) {

    }

    @Override
    public void log(String message) {
        try {
            elysiumChatChannel.log(message);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
