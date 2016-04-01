package com.seventh_root.elysium.characters.wayward

import com.seventh_root.elysium.characters.bukkit.ElysiumCharactersBukkit
import com.seventh_root.elysium.characters.bukkit.character.BukkitCharacter
import com.seventh_root.elysium.characters.bukkit.character.BukkitCharacterProvider
import com.seventh_root.elysium.characters.bukkit.gender.BukkitGender
import com.seventh_root.elysium.characters.bukkit.gender.BukkitGenderProvider
import com.seventh_root.elysium.characters.bukkit.race.BukkitRace
import com.seventh_root.elysium.characters.bukkit.race.BukkitRaceProvider
import com.seventh_root.elysium.characters.wayward.character.CharacterWrapper
import com.seventh_root.elysium.characters.wayward.gender.GenderWrapper
import com.seventh_root.elysium.characters.wayward.race.RaceWrapper
import com.seventh_root.elysium.players.bukkit.BukkitPlayerProvider
import net.wayward_realms.waywardlib.character.*
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ElysiumCharactersWayward : JavaPlugin(), CharacterPlugin {

    private var elysiumCharactersBukkit: ElysiumCharactersBukkit? = null
    var elysiumCharacterProvider: BukkitCharacterProvider? = null
        private set
    var elysiumPlayerProvider: BukkitPlayerProvider? = null
        private set
    var elysiumGenderProvider: BukkitGenderProvider? = null
        private set
    var elysiumRaceProvider: BukkitRaceProvider? = null
        private set

    override fun onEnable() {
        elysiumCharactersBukkit = server.pluginManager.getPlugin("elysium-characters-bukkit") as ElysiumCharactersBukkit
        elysiumCharacterProvider = elysiumCharactersBukkit!!.core!!.serviceManager.getServiceProvider(BukkitCharacterProvider::class.java)
        elysiumPlayerProvider = elysiumCharactersBukkit!!.core!!.serviceManager.getServiceProvider(BukkitPlayerProvider::class.java)
        elysiumGenderProvider = elysiumCharactersBukkit!!.core!!.serviceManager.getServiceProvider(BukkitGenderProvider::class.java)
        elysiumRaceProvider = elysiumCharactersBukkit!!.core!!.serviceManager.getServiceProvider(BukkitRaceProvider::class.java)
    }

    override fun getActiveCharacter(player: OfflinePlayer): Character {
        val elysiumPlayer = elysiumPlayerProvider!!.getPlayer(player)
        val elysiumCharacter = elysiumCharacterProvider!!.getActiveCharacter(elysiumPlayer)
        if (elysiumCharacter != null) {
            return CharacterWrapper(this, elysiumCharacter)
        } else {
            val character = createNewCharacter(player)
            if (player.isOnline) {
                setActiveCharacter(player.player, character)
            }
            return character
        }
    }

    override fun setActiveCharacter(player: Player, character: Character) {
        if (character is CharacterWrapper) {
            val elysiumPlayer = elysiumPlayerProvider!!.getPlayer(player)
            elysiumCharacterProvider!!.setActiveCharacter(elysiumPlayer, character.elysiumCharacter)
        }
    }

    override fun getCharacters(player: OfflinePlayer): Collection<Character> {
        val elysiumCharacters = elysiumCharacterProvider!!.getCharacters(elysiumPlayerProvider!!.getPlayer(player))
        return elysiumCharacters.map { character -> CharacterWrapper(this, character) }
    }

    override fun addCharacter(player: OfflinePlayer, character: Character) {
        if (character is CharacterWrapper) {
            elysiumCharacterProvider!!.addCharacter(character.elysiumCharacter)
            val elysiumPlayer = elysiumPlayerProvider!!.getPlayer(player)
            character.elysiumCharacter.player = elysiumPlayer
        }
    }

    override fun removeCharacter(player: OfflinePlayer, character: Character) {
        if (character is CharacterWrapper) {
            elysiumCharacterProvider!!.removeCharacter(character.elysiumCharacter)
        }
    }

    override fun removeCharacter(character: Character) {
        if (character is CharacterWrapper) {
            elysiumCharacterProvider!!.removeCharacter(character.elysiumCharacter)
        }
    }

    override fun createNewCharacter(player: OfflinePlayer): Character {
        val character = BukkitCharacter(
                plugin = elysiumCharactersBukkit!!,
                player = elysiumPlayerProvider!!.getPlayer(player)
        )
        elysiumCharacterProvider!!.addCharacter(character)
        return CharacterWrapper(this, character)
    }

    override fun getCharacter(id: Int): Character? {
        val elysiumCharacter = elysiumCharacterProvider!!.getCharacter(id)
        if (elysiumCharacter != null) {
            return CharacterWrapper(this, elysiumCharacter)
        } else {
            return null
        }
    }

    override fun getRaces(): Collection<Race> {
        val elysiumRaces = elysiumRaceProvider!!.races
        return elysiumRaces.map { RaceWrapper(it) }
    }

    override fun getRace(name: String): Race? {
        val elysiumRace = elysiumRaceProvider!!.getRace(name)
        if (elysiumRace != null) {
            return RaceWrapper(elysiumRace)
        } else {
            return null
        }
    }

    override fun addRace(race: Race) {
        if (race is RaceWrapper) {
            val elysiumRace = race.elysiumRace
            if (elysiumRace is BukkitRace) {
                elysiumRaceProvider!!.addRace(elysiumRace)
            }
        }
    }

    override fun removeRace(race: Race) {
        if (race is RaceWrapper) {
            val elysiumRace = race.elysiumRace
            if (elysiumRace is BukkitRace) {
                elysiumRaceProvider!!.removeRace(elysiumRace)
            }
        }
    }

    override fun getGenders(): Collection<Gender> {
        val elysiumGenders = elysiumGenderProvider!!.genders
        return elysiumGenders.map { GenderWrapper(it) }
    }

    override fun getGender(name: String): Gender? {
        val elysiumGender = elysiumGenderProvider!!.getGender(name)
        if (elysiumGender != null)
            return GenderWrapper(elysiumGender)
        else
            return null
    }

    override fun addGender(gender: Gender) {
        if (gender is GenderWrapper) {
            val elysiumGender = gender.elysiumGender
            if (elysiumGender is BukkitGender) {
                elysiumGenderProvider!!.addGender(elysiumGender)
            }
        }
    }

    override fun removeGender(gender: Gender) {
        if (gender is GenderWrapper) {
            val elysiumGender = gender.elysiumGender
            if (elysiumGender is BukkitGender) {
                elysiumGenderProvider!!.removeGender(elysiumGender)
            }
        }
    }

    override fun getNextAvailableId(): Int {
        return 0
    }

    override fun setNextAvailableId(id: Int) {

    }

    override fun incrementNextAvailableId() {

    }

    override fun getParty(character: Character): Party? {
        return null
    }

    override fun getPrefix(): String {
        return ""
    }

    override fun loadState() {

    }

    override fun saveState() {

    }
}
