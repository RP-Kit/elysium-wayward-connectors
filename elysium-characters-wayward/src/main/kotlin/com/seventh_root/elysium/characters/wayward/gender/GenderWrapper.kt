package com.seventh_root.elysium.characters.wayward.gender

import net.wayward_realms.waywardlib.character.Gender

class GenderWrapper(val elysiumGender: com.seventh_root.elysium.api.character.Gender) : Gender {

    override fun getName(): String {
        return elysiumGender.name
    }

    override fun serialize(): Map<String, Any>? {
        return null
    }

}
