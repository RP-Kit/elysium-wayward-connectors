package com.seventh_root.elysium.characters.wayward;

import com.seventh_root.elysium.characters.bukkit.ElysiumCharactersBukkit;
import com.seventh_root.elysium.characters.bukkit.character.BukkitCharacter;
import com.seventh_root.elysium.characters.bukkit.character.BukkitCharacterProvider;
import com.seventh_root.elysium.characters.bukkit.gender.BukkitGender;
import com.seventh_root.elysium.characters.bukkit.gender.BukkitGenderProvider;
import com.seventh_root.elysium.characters.bukkit.race.BukkitRace;
import com.seventh_root.elysium.characters.bukkit.race.BukkitRaceProvider;
import com.seventh_root.elysium.characters.wayward.character.CharacterWrapper;
import com.seventh_root.elysium.characters.wayward.gender.GenderWrapper;
import com.seventh_root.elysium.characters.wayward.race.RaceWrapper;
import com.seventh_root.elysium.players.bukkit.BukkitPlayer;
import com.seventh_root.elysium.players.bukkit.BukkitPlayerProvider;
import net.wayward_realms.waywardlib.character.Character;
import net.wayward_realms.waywardlib.character.*;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.stream.Collectors;

public class ElysiumCharactersWayward extends JavaPlugin implements CharacterPlugin {

    private ElysiumCharactersBukkit elysiumCharactersBukkit;
    private BukkitCharacterProvider elysiumCharacterProvider;
    private BukkitPlayerProvider elysiumPlayerProvider;
    private BukkitGenderProvider elysiumGenderProvider;
    private BukkitRaceProvider elysiumRaceProvider;

    @Override
    public void onEnable() {
        elysiumCharactersBukkit = (ElysiumCharactersBukkit) getServer().getPluginManager().getPlugin("elysium-characters-bukkit");
        elysiumCharacterProvider = elysiumCharactersBukkit.getCore().getServiceManager().getServiceProvider(BukkitCharacterProvider.class);
        elysiumPlayerProvider = elysiumCharactersBukkit.getCore().getServiceManager().getServiceProvider(BukkitPlayerProvider.class);
        elysiumGenderProvider = elysiumCharactersBukkit.getCore().getServiceManager().getServiceProvider(BukkitGenderProvider.class);
        elysiumRaceProvider = elysiumCharactersBukkit.getCore().getServiceManager().getServiceProvider(BukkitRaceProvider.class);
    }

    public BukkitCharacterProvider getElysiumCharacterProvider() {
        return elysiumCharacterProvider;
    }

    public BukkitPlayerProvider getElysiumPlayerProvider() {
        return elysiumPlayerProvider;
    }

    public BukkitGenderProvider getElysiumGenderProvider() {
        return elysiumGenderProvider;
    }

    public BukkitRaceProvider getElysiumRaceProvider() {
        return elysiumRaceProvider;
    }

    @Override
    public Character getActiveCharacter(OfflinePlayer player) {
        BukkitPlayer elysiumPlayer = getElysiumPlayerProvider().getPlayer(player);
        BukkitCharacter elysiumCharacter = getElysiumCharacterProvider().getActiveCharacter(elysiumPlayer);
        return new CharacterWrapper(this, elysiumCharacter);
    }

    @Override
    public void setActiveCharacter(Player player, Character character) {
        if (character instanceof CharacterWrapper) {
            BukkitPlayer elysiumPlayer = getElysiumPlayerProvider().getPlayer(player);
            getElysiumCharacterProvider().setActiveCharacter(elysiumPlayer, ((CharacterWrapper) character).getElysiumCharacter());
        }
    }

    @Override
    public Collection<? extends Character> getCharacters(OfflinePlayer player) {
        Collection<? extends BukkitCharacter> elysiumCharacters = getElysiumCharacterProvider().getCharacters(getElysiumPlayerProvider().getPlayer(player));
        return elysiumCharacters.stream().map(character -> new CharacterWrapper(this, character)).collect(Collectors.toList());
    }

    @Override
    public void addCharacter(OfflinePlayer player, Character character) {
        if (character instanceof CharacterWrapper) {
            CharacterWrapper characterWrapper = (CharacterWrapper) character;
            getElysiumCharacterProvider().addCharacter(characterWrapper.getElysiumCharacter());
            BukkitPlayer elysiumPlayer = getElysiumPlayerProvider().getPlayer(player);
            characterWrapper.getElysiumCharacter().setPlayer(elysiumPlayer);
        }
    }

    @Override
    public void removeCharacter(OfflinePlayer player, Character character) {
        if (character instanceof CharacterWrapper) {
            CharacterWrapper characterWrapper = (CharacterWrapper) character;
            characterWrapper.getElysiumCharacter().setPlayer(null);
        }
    }

    @Override
    public void removeCharacter(Character character) {
        if (character instanceof CharacterWrapper) {
            getElysiumCharacterProvider().removeCharacter(((CharacterWrapper) character).getElysiumCharacter());
        }
    }

    @Override
    public Character createNewCharacter(OfflinePlayer player) {
        BukkitCharacter character = new BukkitCharacter.Builder(elysiumCharactersBukkit)
                .player(getElysiumPlayerProvider().getPlayer(player))
                .build();
        getElysiumCharacterProvider().addCharacter(character);
        return new CharacterWrapper(this, character);
    }

    @Override
    public Character getCharacter(int id) {
        return new CharacterWrapper(this, getElysiumCharacterProvider().getCharacter(id));
    }

    @Override
    public Collection<? extends Race> getRaces() {
        Collection<? extends BukkitRace> elysiumRaces = getElysiumRaceProvider().getRaces();
        return elysiumRaces.stream().map(RaceWrapper::new).collect(Collectors.toList());
    }

    @Override
    public Race getRace(String name) {
        return new RaceWrapper(getElysiumRaceProvider().getRace(name));
    }

    @Override
    public void addRace(Race race) {
        if (race instanceof RaceWrapper) {
            com.seventh_root.elysium.api.character.Race elysiumRace = ((RaceWrapper) race).getElysiumRace();
            if (elysiumRace instanceof BukkitRace) {
                getElysiumRaceProvider().addRace((BukkitRace) elysiumRace);
            }
        }
    }

    @Override
    public void removeRace(Race race) {
        if (race instanceof RaceWrapper) {
            com.seventh_root.elysium.api.character.Race elysiumRace = ((RaceWrapper) race).getElysiumRace();
            if (elysiumRace instanceof BukkitRace) {
                getElysiumRaceProvider().removeRace((BukkitRace) elysiumRace);
            }
        }
    }

    @Override
    public Collection<? extends Gender> getGenders() {
        Collection<? extends BukkitGender> elysiumGenders = getElysiumGenderProvider().getGenders();
        return elysiumGenders.stream().map(GenderWrapper::new).collect(Collectors.toList());
    }

    @Override
    public Gender getGender(String name) {
        return new GenderWrapper(getElysiumGenderProvider().getGender(name));
    }

    @Override
    public void addGender(Gender gender) {
        if (gender instanceof GenderWrapper) {
            com.seventh_root.elysium.api.character.Gender elysiumGender = ((GenderWrapper) gender).getElysiumGender();
            if (elysiumGender instanceof BukkitGender) {
                getElysiumGenderProvider().addGender((BukkitGender) elysiumGender);
            }
        }
    }

    @Override
    public void removeGender(Gender gender) {
        if (gender instanceof GenderWrapper) {
            com.seventh_root.elysium.api.character.Gender elysiumGender = ((GenderWrapper) gender).getElysiumGender();
            if (elysiumGender instanceof BukkitGender) {
                getElysiumGenderProvider().removeGender((BukkitGender) elysiumGender);
            }
        }
    }

    @Override
    public int getNextAvailableId() {
        return 0;
    }

    @Override
    public void setNextAvailableId(int id) {

    }

    @Override
    public void incrementNextAvailableId() {

    }

    @Override
    public Party getParty(Character character) {
        return null;
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
