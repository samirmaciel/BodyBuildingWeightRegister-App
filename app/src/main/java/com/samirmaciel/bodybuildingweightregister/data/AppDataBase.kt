package com.samirmaciel.bodybuildingweightregister.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samirmaciel.bodybuildingweightregister.domain.dao.EquipamentModelDao
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel

@Database(entities = [EquipamentModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getEquipamentModelDao() : EquipamentModelDao

    companion object {
        const val DATABASE_NAME = "bodybuildingweightregister_db"

        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getDatabase(context: Context) : AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }

}