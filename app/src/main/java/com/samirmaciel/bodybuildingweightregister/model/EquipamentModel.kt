package com.samirmaciel.bodybuildingweightregister.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EquipamentModel(

    @PrimaryKey (autoGenerate = true)
    var id: Long = 0,

    var name: String,

    var weight: String,

    var category: String
)
