package com.samirmaciel.bodybuildingweightregister.domain.repository

import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel

interface EquipamentRepository {

    suspend fun findAllEquipament(): List<EquipamentModel>

    suspend fun findById(id: Long): EquipamentModel?

    suspend fun insertEquipament(equipamentModel: EquipamentModel): Long

    suspend fun deleteEquipament(equipamentModel: EquipamentModel)
}