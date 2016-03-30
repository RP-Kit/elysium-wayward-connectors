package com.seventh_root.elysium.characters.wayward.race

import net.wayward_realms.waywardlib.character.Race
import net.wayward_realms.waywardlib.classes.Stat

class RaceWrapper(val elysiumRace: com.seventh_root.elysium.api.character.Race) : Race {

    override fun getName(): String {
        return elysiumRace.name
    }

    override fun getStatBonus(stat: Stat): Int {
        return 0
    }

    override fun serialize(): Map<String, Any>? {
        return null
    }

}
