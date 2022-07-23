package com.samirmaciel.bodybuildingweightregister.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.bodybuildingweightregister.R
import com.samirmaciel.bodybuildingweightregister.databinding.AddEquipamentBottomsheetFragmentBinding
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel

class AddEquipamentBottomSheet(val isEdit: Boolean, val equipamentModel: EquipamentModel?, val onFinish: (EquipamentModel) -> Unit): BottomSheetDialogFragment() {

    private var _binding: AddEquipamentBottomsheetFragmentBinding? = null
    private val binding: AddEquipamentBottomsheetFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddEquipamentBottomsheetFragmentBinding.bind(inflater.inflate(
            R.layout.add_equipament_bottomsheet_fragment, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()

        if(isEdit){
            if(equipamentModel != null){
                Log.d("TESTE", "onViewCreated: " + equipamentModel.id)
                bindItemView(equipamentModel)
            }
        }
    }

    private fun bindItemView(equipamentModel: EquipamentModel){
        binding.edtEquipamentName.setText(equipamentModel.name)
        binding.edtEquipamentWeight.setText(equipamentModel.weight)
        binding.edtEquipamentCategory.visibility = View.GONE
    }
    private fun setListeners(){

        binding.btnSave.setOnClickListener {
            val newEquipament = getEquipament()

            if(newEquipament != null){
                onFinish(newEquipament)
                dismiss()
            }else{
                Toast.makeText(requireContext(), "Preencha corretamente todos os campos!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getEquipament(): EquipamentModel? {
        val name = binding.edtEquipamentName.text.toString()
        val weight = binding.edtEquipamentWeight.text.toString()
        val category = binding.edtEquipamentCategory.text.toString()

        if(weight.isNullOrEmpty() || name.isNullOrEmpty()) return null

        if(!isEdit){
            if(category.isNullOrEmpty()) return null
        }

        val newEquipament = EquipamentModel(name = name, weight = weight, category = category)

        if(isEdit){
            if(equipamentModel != null){
                equipamentModel.weight = weight
                equipamentModel.name = name
                return equipamentModel
            }
        }

        return newEquipament
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}