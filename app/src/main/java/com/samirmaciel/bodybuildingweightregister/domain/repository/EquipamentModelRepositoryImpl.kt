package com.samirmaciel.bodybuildingweightregister.domain.repository

import com.samirmaciel.bodybuildingweightregister.domain.dao.EquipamentModelDao
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel

class EquipamentModelRepositoryImpl(
    private val equipamentModelDao: EquipamentModelDao
) : EquipamentRepository {

    override suspend fun findAllEquipament(): List<EquipamentModel> {
        return equipamentModelDao.findAllEquipamentModel()
    }

    override suspend fun findById(id: Long): EquipamentModel? {
        return equipamentModelDao.findById(id)
    }

    override suspend fun insertEquipament(equipamentModel: EquipamentModel): Long {
        return equipamentModelDao.insertEquipamentModel(equipamentModel)
    }

    override suspend fun deleteEquipament(equipamentModel: EquipamentModel) {
        equipamentModelDao.deleteEquipamentModel(equipamentModel)
    }
}