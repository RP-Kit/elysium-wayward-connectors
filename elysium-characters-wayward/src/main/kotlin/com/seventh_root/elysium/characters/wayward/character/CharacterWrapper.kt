package com.seventh_root.elysium.characters.wayward.character

import com.seventh_root.elysium.characters.bukkit.character.BukkitCharacter
import com.seventh_root.elysium.characters.wayward.ElysiumCharactersWayward
import com.seventh_root.elysium.characters.wayward.gender.GenderWrapper
import com.seventh_root.elysium.characters.wayward.race.RaceWrapper
import com.seventh_root.elysium.players.bukkit.BukkitPlayer
import net.wayward_realms.waywardlib.character.Character
import net.wayward_realms.waywardlib.character.Gender
import net.wayward_realms.waywardlib.character.Race
import net.wayward_realms.waywardlib.character.TemporaryStatModification
import net.wayward_realms.waywardlib.classes.Stat
import net.wayward_realms.waywardlib.skills.SkillType
import org.bukkit.Location
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import java.util.*

class CharacterWrapper(private val plugin: ElysiumCharactersWayward, val elysiumCharacter: BukkitCharacter) : Character {

    override fun getId(): Int {
        return elysiumCharacter.id
    }

    override fun getName(): String {
        return elysiumCharacter.name
    }

    override fun setName(name: String) {
        elysiumCharacter.name = name
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun isNameHidden(): Boolean {
        return false
    }

    override fun setNameHidden(hidden: Boolean) {

    }

    override fun getAge(): Int {
        return elysiumCharacter.age
    }

    override fun setAge(age: Int) {
        elysiumCharacter.age = age
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun isAgeHidden(): Boolean {
        return false
    }

    override fun setAgeHidden(hidden: Boolean) {

    }

    override fun getGender(): Gender {
        return GenderWrapper(elysiumCharacter.gender)
    }

    override fun setGender(gender: Gender) {
        if (gender is GenderWrapper) {
            elysiumCharacter.gender = gender.elysiumGender
            plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
        }
    }

    override fun isGenderHidden(): Boolean {
        return false
    }

    override fun setGenderHidden(hidden: Boolean) {

    }

    override fun getRace(): Race {
        return RaceWrapper(elysiumCharacter.race)
    }

    override fun setRace(race: Race) {
        if (race is RaceWrapper) {
            elysiumCharacter.race = race.elysiumRace
            plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
        }
    }

    override fun isRaceHidden(): Boolean {
        return false
    }

    override fun setRaceHidden(hidden: Boolean) {

    }

    override fun getDescription(): String {
        return elysiumCharacter.description
    }

    override fun setDescription(info: String) {
        elysiumCharacter.description = info
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun addDescription(info: String) {
        elysiumCharacter.description = elysiumCharacter.description + info
    }

    override fun isDescriptionHidden(): Boolean {
        return false
    }

    override fun setDescriptionHidden(hidden: Boolean) {

    }

    override fun getPlayer(): OfflinePlayer? {
        val player = elysiumCharacter.player
        if (player is BukkitPlayer) {
            return (elysiumCharacter.player as BukkitPlayer).bukkitPlayer
        }
        return null
    }

    override fun setPlayer(player: OfflinePlayer) {
        elysiumCharacter.player = plugin.elysiumPlayerProvider!!.getPlayer(player)
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getHealth(): Double {
        return elysiumCharacter.health
    }

    override fun setHealth(health: Double) {
        elysiumCharacter.health = health
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getFoodLevel(): Int {
        return elysiumCharacter.foodLevel
    }

    override fun setFoodLevel(foodLevel: Int) {
        elysiumCharacter.foodLevel = foodLevel
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getThirst(): Int {
        return elysiumCharacter.thirstLevel
    }

    override fun setThirst(thirstLevel: Int) {
        elysiumCharacter.thirstLevel = thirstLevel
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getMaxHealth(): Double {
        return elysiumCharacter.maxHealth
    }

    override fun getMana(): Int {
        return elysiumCharacter.mana
    }

    override fun setMana(mana: Int) {
        elysiumCharacter.mana = mana
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getMaxMana(): Int {
        return elysiumCharacter.maxMana
    }

    override fun getLocation(): Location {
        return elysiumCharacter.location
    }

    override fun setLocation(location: Location) {
        elysiumCharacter.location = location
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getHelmet(): ItemStack? {
        return elysiumCharacter.helmet
    }

    override fun setHelmet(helmet: ItemStack) {
        elysiumCharacter.helmet = helmet
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getChestplate(): ItemStack? {
        return elysiumCharacter.chestplate
    }

    override fun setChestplate(chestplate: ItemStack) {
        elysiumCharacter.chestplate = chestplate
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getLeggings(): ItemStack? {
        return elysiumCharacter.leggings
    }

    override fun setLeggings(leggings: ItemStack) {
        elysiumCharacter.leggings = leggings
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getBoots(): ItemStack? {
        return elysiumCharacter.boots
    }

    override fun setBoots(boots: ItemStack) {
        elysiumCharacter.boots = boots
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getInventoryContents(): Array<ItemStack> {
        return elysiumCharacter.inventoryContents
    }

    override fun setInventoryContents(contents: Array<ItemStack>) {
        elysiumCharacter.inventoryContents = contents
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun isDead(): Boolean {
        return elysiumCharacter.isDead
    }

    override fun setDead(dead: Boolean) {
        elysiumCharacter.isDead = dead
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }

    override fun getStatValue(stat: Stat): Int {
        return 0
    }

    override fun getTemporaryStatModifications(): Collection<TemporaryStatModification> {
        return ArrayList()
    }

    override fun addTemporaryStatModification(modification: TemporaryStatModification) {

    }

    override fun removeTemporaryStatModification(modification: TemporaryStatModification) {

    }

    override fun getSkillPoints(type: SkillType): Int {
        return 0
    }

    override fun isClassHidden(): Boolean {
        return false
    }

    override fun setClassHidden(hidden: Boolean) {

    }

    override fun getNamePlate(): String {
        return elysiumCharacter.name
    }

    override fun setNamePlate(namePlate: String) {
        elysiumCharacter.name = namePlate
        plugin.elysiumCharacterProvider!!.updateCharacter(elysiumCharacter)
    }
}
