package me.magnum.melonds.domain.model

import java.util.*

data class SaveStateSlot(val slot: Int, val exists: Boolean, val path: String, val lastUsedDate: Date?)