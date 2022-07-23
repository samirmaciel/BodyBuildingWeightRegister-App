package com.samirmaciel.bodybuildingweightregister.domain.dao

import androidx.room.*
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel

@Dao
interface EquipamentModelDao {

    @Transaction
    @Query("SELECT * FROM EquipamentModel")
    suspend fun findAllEquipamentModel() : List<EquipamentModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipamentModel(equipamentModel: EquipamentModel) : Long

    @Delete
    suspend fun deleteEquipamentModel(marketlist: EquipamentModel)

    @Transaction
    @Query("SELECT * FROM EquipamentModel WHERE id = :id")
    suspend fun findById(id : Long) : EquipamentModel?

}
