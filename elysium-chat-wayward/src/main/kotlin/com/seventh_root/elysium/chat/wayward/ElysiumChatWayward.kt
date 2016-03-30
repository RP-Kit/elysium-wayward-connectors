package com.seventh_root.elysium.chat.wayward

import com.seventh_root.elysium.chat.bukkit.ElysiumChatBukkit
import com.seventh_root.elysium.chat.bukkit.chatchannel.BukkitChatChannelProvider
import com.seventh_root.elysium.chat.wayward.chatchannel.ChatChannelWrapper
import com.seventh_root.elysium.players.bukkit.BukkitPlayerProvider
import net.wayward_realms.waywardlib.chat.Channel
import net.wayward_realms.waywardlib.chat.ChatGroup
import net.wayward_realms.waywardlib.chat.ChatPlugin
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class ElysiumChatWayward : JavaPlugin(), ChatPlugin {

    private var elysiumChatBukkit: ElysiumChatBukkit? = null
    var elysiumChatChannelProvider: BukkitChatChannelProvider? = null
        private set
    var elysiumPlayerProvider: BukkitPlayerProvider? = null
        private set

    override fun onEnable() {
        elysiumChatBukkit = server.pluginManager.getPlugin("elysium-chat-bukkit") as ElysiumChatBukkit
        elysiumChatChannelProvider = elysiumChatBukkit!!.core!!.serviceManager.getServiceProvider(BukkitChatChannelProvider::class.java)
        elysiumPlayerProvider = elysiumChatBukkit!!.core!!.serviceManager.getServiceProvider(BukkitPlayerProvider::class.java)
    }

    override fun getChannels(): Collection<Channel> {
        return elysiumChatChannelProvider!!.chatChannels.map { elysiumChatChannel -> ChatChannelWrapper(this, elysiumChatChannel) }
    }

    override fun getChannel(name: String): Channel? {
        val elysiumChatChannel = elysiumChatChannelProvider!!.getChatChannel(name)
        if (elysiumChatChannel != null) {
            return ChatChannelWrapper(this, elysiumChatChannel)
        } else {
            return null
        }
    }

    override fun addChannel(channel: Channel) {
        if (channel is ChatChannelWrapper) {
            elysiumChatChannelProvider!!.addChatChannel(channel.elysiumChatChannel)
        }
    }

    override fun removeChannel(channel: Channel) {
        if (channel is ChatChannelWrapper) {
            elysiumChatChannelProvider!!.removeChatChannel(channel.elysiumChatChannel)
        }
    }

    override fun getPlayerChannel(player: Player): Channel? {
        val elysiumPlayer = elysiumPlayerProvider!!.getPlayer(player)
        val elysiumChatChannel = elysiumChatChannelProvider!!.getPlayerChannel(elysiumPlayer)
        if (elysiumChatChannel != null) {
            return ChatChannelWrapper(this, elysiumChatChannel)
        } else {
            return null
        }
    }

    override fun setPlayerChannel(player: Player, channel: Channel) {
        if (channel is ChatChannelWrapper) {
            val elysiumPlayer = elysiumPlayerProvider!!.getPlayer(player)
            elysiumChatChannelProvider!!.setPlayerChannel(elysiumPlayer, channel.elysiumChatChannel)
        }
    }

    override fun getChannelFromIrcChannel(ircChannel: String): Channel? {
        val elysiumChatChannel = elysiumChatChannelProvider!!.getChatChannelFromIRCChannel(ircChannel)
        if (elysiumChatChannel != null) {
            return ChatChannelWrapper(this, elysiumChatChannel)
        } else {
            return null
        }
    }

    override fun getUsersInIrcChannel(ircChannel: String): Collection<String> {
        return ArrayList()
    }

    override fun getStaffInIrcChannel(ircChannel: String): Collection<String> {
        return ArrayList()
    }

    override fun getChatGroup(name: String): ChatGroup? {
        return null
    }

    override fun removeChatGroup(chatGroup: ChatGroup) {

    }

    override fun addChatGroup(chatGroup: ChatGroup) {

    }

    override fun getPrefix(): String {
        return ""
    }

    override fun loadState() {

    }

    override fun saveState() {

    }

}
