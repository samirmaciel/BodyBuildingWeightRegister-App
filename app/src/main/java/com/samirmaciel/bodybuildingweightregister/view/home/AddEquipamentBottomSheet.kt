package com.samirmaciel.bodybuildingweightregister.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.bodybuildingweightregister.R
import com.samirmaciel.bodybuildingweightregister.databinding.AddEquipamentBottomsheetFragmentBinding
import com.samirmaciel.bodybuildingweightregister.model.EquipamentModel

class AddEquipamentBottomSheet(val isEdit: Boolean, val equipamentModel: EquipamentModel?, val onFinish: (EquipamentModel) -> Unit): BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var _binding: AddEquipamentBottomsheetFragmentBinding? = null
    private val binding: AddEquipamentBottomsheetFragmentBinding get() = _binding!!
    private var selectedCategory: String? = null
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
        initSpinner()
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
        binding.spinnerCategory.visibility = View.GONE
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
        var newEquipament: EquipamentModel? = null
        val name = binding.edtEquipamentName.text.toString()
        val weight = binding.edtEquipamentWeight.text.toString()

        if(weight.isNullOrEmpty() || name.isNullOrEmpty()) return null

        if(selectedCategory != null){
            newEquipament = EquipamentModel(name = name, weight = weight, category = selectedCategory!!)
        }

        if(isEdit){
            if(equipamentModel != null){
                equipamentModel.weight = weight
                equipamentModel.name = name
                return equipamentModel
            }
        }

        return newEquipament
    }

    private fun initSpinner(){
        val spinner = binding.spinnerCategory
        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.categories, android.R.layout.simple_spinner_item )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        selectedCategory = p0?.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}