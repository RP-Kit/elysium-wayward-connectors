package com.seventh_root.elysium.chat.wayward.chatchannel

import com.seventh_root.elysium.chat.bukkit.chatchannel.BukkitChatChannel
import com.seventh_root.elysium.chat.wayward.ElysiumChatWayward
import com.seventh_root.elysium.core.bukkit.util.ChatColorUtils
import com.seventh_root.elysium.players.bukkit.BukkitPlayer
import net.wayward_realms.waywardlib.chat.Channel
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.entity.Player
import java.io.IOException

class ChatChannelWrapper(private val plugin: ElysiumChatWayward, val elysiumChatChannel: BukkitChatChannel) : Channel {

    override fun getName(): String {
        return elysiumChatChannel.name
    }

    override fun setName(name: String) {
        elysiumChatChannel.name = name
    }

    override fun getColour(): ChatColor {
        return ChatColorUtils.closestChatColorToColor(elysiumChatChannel.color)
    }

    override fun setColour(colour: ChatColor) {
        elysiumChatChannel.color = ChatColorUtils.colorFromChatColor(colour)!!
    }

    override fun getFormat(): String {
        return elysiumChatChannel.formatString
    }

    override fun setFormat(format: String) {
        elysiumChatChannel.formatString = format
    }

    override fun getRadius(): Int {
        return elysiumChatChannel.radius
    }

    override fun setRadius(radius: Int) {
        elysiumChatChannel.radius = radius
    }

    override fun getSpeakers(): Collection<Player> {
        return elysiumChatChannel.speakers
                .filter { elysiumPlayer -> elysiumPlayer is BukkitPlayer }
                .map { elysiumPlayer -> (elysiumPlayer as BukkitPlayer).bukkitPlayer }
                .filter { it.isOnline }
                .map { it.player }
    }

    override fun addSpeaker(speaker: Player) {
        val elysiumPlayer = plugin.elysiumPlayerProvider!!.getPlayer(speaker)
        elysiumChatChannel.addSpeaker(elysiumPlayer)
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun removeSpeaker(speaker: Player) {
        val elysiumPlayer = plugin.elysiumPlayerProvider!!.getPlayer(speaker)
        elysiumChatChannel.removeSpeaker(elysiumPlayer)
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun getListeners(): Collection<Player> {
        return elysiumChatChannel.listeners
                .filter { elysiumPlayer -> elysiumPlayer is BukkitPlayer }
                .map { elysiumPlayer -> (elysiumPlayer as BukkitPlayer).bukkitPlayer }
                .filter { it.isOnline }
                .map { it.player }
    }

    override fun addListener(listener: Player) {
        val elysiumPlayer = plugin.elysiumPlayerProvider!!.getPlayer(listener)
        elysiumChatChannel.addListener(elysiumPlayer)
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun removeListener(listener: Player) {
        val elysiumPlayer = plugin.elysiumPlayerProvider!!.getPlayer(listener)
        elysiumChatChannel.removeListener(elysiumPlayer)
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun isGarbleEnabled(): Boolean {
        return elysiumChatChannel.clearRadius > 0
    }

    override fun setGarbleEnabled(enable: Boolean) {
        elysiumChatChannel.clearRadius = Math.round(elysiumChatChannel.radius * 0.75).toInt()
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun isIrcEnabled(): Boolean {
        return elysiumChatChannel.isIRCEnabled
    }

    override fun setIrcEnabled(enable: Boolean) {
        elysiumChatChannel.isIRCEnabled = enable
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun getIrcChannel(): String? {
        return elysiumChatChannel.ircChannel
    }

    override fun setIrcChannel(channel: String) {
        elysiumChatChannel.ircChannel = channel
        plugin.elysiumChatChannelProvider!!.updateChatChannel(elysiumChatChannel)
    }

    override fun getCommand(): Command? {
        return null
    }

    override fun setCommand(command: Command) {

    }

    override fun log(message: String) {
        try {
            elysiumChatChannel.log(message)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }

    }
}
