package com.seventh_root.elysium.characters.wayward.race;

import net.wayward_realms.waywardlib.character.Race;
import net.wayward_realms.waywardlib.classes.Stat;

import java.util.Map;

public class RaceWrapper implements Race {
    private final com.seventh_root.elysium.api.character.Race elysiumRace;

    public RaceWrapper(com.seventh_root.elysium.api.character.Race elysiumRace) {
        this.elysiumRace = elysiumRace;
    }

    public com.seventh_root.elysium.api.character.Race getElysiumRace() {
        return elysiumRace;
    }

    @Override
    public String getName() {
        return elysiumRace.getName();
    }

    @Override
    public int getStatBonus(Stat stat) {
        return 0;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

}
