package com.seventh_root.elysium.characters.wayward.character;

import com.seventh_root.elysium.api.player.ElysiumPlayer;
import com.seventh_root.elysium.characters.bukkit.character.BukkitCharacter;
import com.seventh_root.elysium.characters.wayward.ElysiumCharactersWayward;
import com.seventh_root.elysium.characters.wayward.gender.GenderWrapper;
import com.seventh_root.elysium.characters.wayward.race.RaceWrapper;
import com.seventh_root.elysium.players.bukkit.BukkitPlayer;
import net.wayward_realms.waywardlib.character.Character;
import net.wayward_realms.waywardlib.character.Gender;
import net.wayward_realms.waywardlib.character.Race;
import net.wayward_realms.waywardlib.character.TemporaryStatModification;
import net.wayward_realms.waywardlib.classes.Stat;
import net.wayward_realms.waywardlib.skills.SkillType;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class CharacterWrapper implements Character {

    private final ElysiumCharactersWayward plugin;
    private final BukkitCharacter elysiumCharacter;

    public CharacterWrapper(ElysiumCharactersWayward plugin, BukkitCharacter elysiumCharacter) {
        this.plugin = plugin;
        this.elysiumCharacter = elysiumCharacter;
    }

    public BukkitCharacter getElysiumCharacter() {
        return elysiumCharacter;
    }

    @Override
    public int getId() {
        return elysiumCharacter.getId();
    }

    @Override
    public String getName() {
        return elysiumCharacter.getName();
    }

    @Override
    public void setName(String name) {
        elysiumCharacter.setName(name);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public boolean isNameHidden() {
        return false;
    }

    @Override
    public void setNameHidden(boolean hidden) {

    }

    @Override
    public int getAge() {
        return elysiumCharacter.getAge();
    }

    @Override
    public void setAge(int age) {
        elysiumCharacter.setAge(age);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public boolean isAgeHidden() {
        return false;
    }

    @Override
    public void setAgeHidden(boolean hidden) {

    }

    @Override
    public Gender getGender() {
        return new GenderWrapper(elysiumCharacter.getGender());
    }

    @Override
    public void setGender(Gender gender) {
        if (gender instanceof GenderWrapper) {
            elysiumCharacter.setGender(((GenderWrapper) gender).getElysiumGender());
            plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
        }
    }

    @Override
    public boolean isGenderHidden() {
        return false;
    }

    @Override
    public void setGenderHidden(boolean hidden) {

    }

    @Override
    public Race getRace() {
        return new RaceWrapper(elysiumCharacter.getRace());
    }

    @Override
    public void setRace(Race race) {
        if (race instanceof RaceWrapper) {
            elysiumCharacter.setRace(((RaceWrapper) race).getElysiumRace());
            plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
        }
    }

    @Override
    public boolean isRaceHidden() {
        return false;
    }

    @Override
    public void setRaceHidden(boolean hidden) {

    }

    @Override
    public String getDescription() {
        return elysiumCharacter.getDescription();
    }

    @Override
    public void setDescription(String info) {
        elysiumCharacter.setDescription(info);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public void addDescription(String info) {
        elysiumCharacter.setDescription(elysiumCharacter.getDescription() + info);
    }

    @Override
    public boolean isDescriptionHidden() {
        return false;
    }

    @Override
    public void setDescriptionHidden(boolean hidden) {

    }

    @Override
    public OfflinePlayer getPlayer() {
        ElysiumPlayer player = elysiumCharacter.getPlayer();
        if (player instanceof BukkitPlayer) {
            return ((BukkitPlayer) elysiumCharacter.getPlayer()).getBukkitPlayer();
        }
        return null;
    }

    @Override
    public void setPlayer(OfflinePlayer player) {
        elysiumCharacter.setPlayer(plugin.getElysiumPlayerProvider().getPlayer(player));
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public double getHealth() {
        return elysiumCharacter.getHealth();
    }

    @Override
    public void setHealth(double health) {
        elysiumCharacter.setHealth(health);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public int getFoodLevel() {
        return elysiumCharacter.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int foodLevel) {
        elysiumCharacter.setFoodLevel(foodLevel);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public int getThirst() {
        return elysiumCharacter.getThirstLevel();
    }

    @Override
    public void setThirst(int thirstLevel) {
        elysiumCharacter.setThirstLevel(thirstLevel);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public double getMaxHealth() {
        return elysiumCharacter.getMaxHealth();
    }

    @Override
    public int getMana() {
        return elysiumCharacter.getMana();
    }

    @Override
    public void setMana(int mana) {
        elysiumCharacter.setMana(mana);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public int getMaxMana() {
        return elysiumCharacter.getMaxMana();
    }

    @Override
    public Location getLocation() {
        return elysiumCharacter.getLocation();
    }

    @Override
    public void setLocation(Location location) {
        elysiumCharacter.setLocation(location);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public ItemStack getHelmet() {
        return elysiumCharacter.getHelmet();
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        elysiumCharacter.setHelmet(helmet);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public ItemStack getChestplate() {
        return elysiumCharacter.getChestplate();
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        elysiumCharacter.setChestplate(chestplate);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public ItemStack getLeggings() {
        return elysiumCharacter.getLeggings();
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        elysiumCharacter.setLeggings(leggings);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public ItemStack getBoots() {
        return elysiumCharacter.getBoots();
    }

    @Override
    public void setBoots(ItemStack boots) {
        elysiumCharacter.setBoots(boots);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public ItemStack[] getInventoryContents() {
        return elysiumCharacter.getInventoryContents();
    }

    @Override
    public void setInventoryContents(ItemStack[] contents) {
        elysiumCharacter.setInventoryContents(contents);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public boolean isDead() {
        return elysiumCharacter.isDead();
    }

    @Override
    public void setDead(boolean dead) {
        elysiumCharacter.setDead(dead);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }

    @Override
    public int getStatValue(Stat stat) {
        return 0;
    }

    @Override
    public Collection<TemporaryStatModification> getTemporaryStatModifications() {
        return new ArrayList<>();
    }

    @Override
    public void addTemporaryStatModification(TemporaryStatModification modification) {

    }

    @Override
    public void removeTemporaryStatModification(TemporaryStatModification modification) {

    }

    @Override
    public int getSkillPoints(SkillType type) {
        return 0;
    }

    @Override
    public boolean isClassHidden() {
        return false;
    }

    @Override
    public void setClassHidden(boolean hidden) {

    }

    @Override
    public String getNamePlate() {
        return elysiumCharacter.getName();
    }

    @Override
    public void setNamePlate(String namePlate) {
        elysiumCharacter.setName(namePlate);
        plugin.getElysiumCharacterProvider().updateCharacter(elysiumCharacter);
    }
}
