package com.samirmaciel.bodybuildingweightregister.di

import android.content.Context
import com.samirmaciel.bodybuildingweightregister.data.AppDataBase
import com.samirmaciel.bodybuildingweightregister.domain.repository.EquipamentModelRepositoryImpl
import com.samirmaciel.bodybuildingweightregister.domain.repository.EquipamentRepository
import com.samirmaciel.bodybuildingweightregister.view.home.viewModel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideEquipamentModelRepository(@ApplicationContext appContext: Context) : EquipamentRepository {
        return EquipamentModelRepositoryImpl(AppDataBase.getDatabase(appContext).getEquipamentModelDao())
    }

    @Singleton
    @Provides
    fun provideHomeViewModel(
        equipamentModelRepositoryImpl: EquipamentRepository,

    ) : HomeViewModel{
        return HomeViewModel(equipamentModelRepositoryImpl)
    }

}