package com.samirmaciel.bodybuildingweightregister.view.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samirmaciel.bodybuildingweightregister.domain.repository.EquipamentRepository
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val equipamentModelRepositoryImpl: EquipamentRepository,
): ViewModel() {

    val equipamentModelListA: MutableLiveData<MutableList<EquipamentModel>> = MutableLiveData()
    val equipamentModelListB: MutableLiveData<MutableList<EquipamentModel>> = MutableLiveData()
    val equipamentModelListC: MutableLiveData<MutableList<EquipamentModel>> = MutableLiveData()
    val onResultMessage: MutableLiveData<String> = MutableLiveData()

    init {
        getEquipamentModelList()
    }

    fun getEquipamentModelList(){
        viewModelScope.launch {
            val equipamentModelList = equipamentModelRepositoryImpl.findAllEquipament().toMutableList()
            val categoryA: MutableList<EquipamentModel> = mutableListOf()
            val categoryB: MutableList<EquipamentModel> = mutableListOf()
            val categoryC: MutableList<EquipamentModel> = mutableListOf()

            for(equipament in equipamentModelList){
                when(equipament.category){
                    "A" -> categoryA.add(equipament)
                    "B" -> categoryB.add(equipament)
                    "C" -> categoryC.add(equipament)
                }
            }

            equipamentModelListA.postValue(categoryA)
            equipamentModelListB.postValue(categoryB)
            equipamentModelListC.postValue(categoryC)
        }
    }

    fun insertEquipamentModel(equipamentModel: EquipamentModel){
        viewModelScope.launch {
            var returnNumber = equipamentModelRepositoryImpl.insertEquipament(equipamentModel)

            if(returnNumber > 0){
                onResultMessage.postValue("Equipamento salvo com sucesso!")
            }else{
                onResultMessage.postValue("Equipamento não foi salvo com sucesso!")
            }
        }.invokeOnCompletion {
            getEquipamentModelList()
        }
    }

    fun deleteEquipamentModel(equipamentModel: EquipamentModel){
        viewModelScope.launch {
            equipamentModelRepositoryImpl.deleteEquipament(equipamentModel)
        }.invokeOnCompletion {
            getEquipamentModelList()
        }
    }

    fun findById(id: Long){
        viewModelScope.launch {
            val equipamentModel = equipamentModelRepositoryImpl.findById(id)
        }
    }

}