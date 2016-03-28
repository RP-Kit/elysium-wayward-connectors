package com.seventh_root.elysium.characters.wayward.gender;

import net.wayward_realms.waywardlib.character.Gender;

import java.util.Map;

public class GenderWrapper implements Gender {

    private final com.seventh_root.elysium.api.character.Gender elysiumGender;

    public GenderWrapper(com.seventh_root.elysium.api.character.Gender elysiumGender) {
        this.elysiumGender = elysiumGender;
    }

    public com.seventh_root.elysium.api.character.Gender getElysiumGender() {
        return elysiumGender;
    }

    @Override
    public String getName() {
        return elysiumGender.getName();
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

}
