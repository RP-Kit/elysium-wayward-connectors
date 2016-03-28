package com.seventh_root.elysium.chat.wayward;

import com.seventh_root.elysium.chat.bukkit.ElysiumChatBukkit;
import com.seventh_root.elysium.chat.bukkit.chatchannel.BukkitChatChannelProvider;
import com.seventh_root.elysium.chat.wayward.chatchannel.ChatChannelWrapper;
import com.seventh_root.elysium.players.bukkit.BukkitPlayer;
import com.seventh_root.elysium.players.bukkit.BukkitPlayerProvider;
import net.wayward_realms.waywardlib.chat.Channel;
import net.wayward_realms.waywardlib.chat.ChatGroup;
import net.wayward_realms.waywardlib.chat.ChatPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ElysiumChatWayward extends JavaPlugin implements ChatPlugin {

    private ElysiumChatBukkit elysiumChatBukkit;
    private BukkitChatChannelProvider elysiumChatChannelProvider;
    private BukkitPlayerProvider elysiumPlayerProvider;

    @Override
    public void onEnable() {
        elysiumChatBukkit = (ElysiumChatBukkit) getServer().getPluginManager().getPlugin("elysium-chat-bukkit");
        elysiumChatChannelProvider = elysiumChatBukkit.getCore().getServiceManager().getServiceProvider(BukkitChatChannelProvider.class);
        elysiumPlayerProvider = elysiumChatBukkit.getCore().getServiceManager().getServiceProvider(BukkitPlayerProvider.class);
    }

    public BukkitChatChannelProvider getElysiumChatChannelProvider() {
        return elysiumChatChannelProvider;
    }

    public BukkitPlayerProvider getElysiumPlayerProvider() {
        return elysiumPlayerProvider;
    }

    @Override
    public Collection<? extends Channel> getChannels() {
        return getElysiumChatChannelProvider().getChatChannels()
                .stream()
                .map(elysiumChatChannel -> new ChatChannelWrapper(this, elysiumChatChannel))
                .collect(Collectors.toList());
    }

    @Override
    public Channel getChannel(String name) {
        return new ChatChannelWrapper(this, getElysiumChatChannelProvider().getChatChannel(name));
    }

    @Override
    public void addChannel(Channel channel) {
        if (channel instanceof ChatChannelWrapper) {
            getElysiumChatChannelProvider().addChatChannel(((ChatChannelWrapper) channel).getElysiumChatChannel());
        }
    }

    @Override
    public void removeChannel(Channel channel) {
        if (channel instanceof ChatChannelWrapper) {
            getElysiumChatChannelProvider().removeChatChannel(((ChatChannelWrapper) channel).getElysiumChatChannel());
        }
    }

    @Override
    public Channel getPlayerChannel(Player player) {
        BukkitPlayer elysiumPlayer = getElysiumPlayerProvider().getPlayer(player);
        return new ChatChannelWrapper(this, getElysiumChatChannelProvider().getPlayerChannel(elysiumPlayer));
    }

    @Override
    public void setPlayerChannel(Player player, Channel channel) {
        if (channel instanceof ChatChannelWrapper) {
            BukkitPlayer elysiumPlayer = getElysiumPlayerProvider().getPlayer(player);
            getElysiumChatChannelProvider().setPlayerChannel(elysiumPlayer, ((ChatChannelWrapper) channel).getElysiumChatChannel());
        }
    }

    @Override
    public Channel getChannelFromIrcChannel(String ircChannel) {
        return new ChatChannelWrapper(this, getElysiumChatChannelProvider().getChatChannelFromIRCChannel(ircChannel));
    }

    @Override
    public Collection<String> getUsersInIrcChannel(String ircChannel) {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> getStaffInIrcChannel(String ircChannel) {
        return new ArrayList<>();
    }

    @Override
    public ChatGroup getChatGroup(String name) {
        return null;
    }

    @Override
    public void removeChatGroup(ChatGroup chatGroup) {

    }

    @Override
    public void addChatGroup(ChatGroup chatGroup) {

    }

    @Override
    public String getPrefix() {
        return "";
    }

    @Override
    public void loadState() {

    }

    @Override
    public void saveState() {

    }

}
