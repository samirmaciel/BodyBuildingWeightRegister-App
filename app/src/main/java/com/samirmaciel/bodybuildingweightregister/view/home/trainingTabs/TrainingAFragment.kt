package com.samirmaciel.bodybuildingweightregister.view.home.trainingTabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.samirmaciel.bodybuildingweightregister.R
import com.samirmaciel.bodybuildingweightregister.databinding.FragmentTrainingABinding
import com.samirmaciel.bodybuildingweightregister.view.home.adapter.EquipamentModelsRecyclerAdapter
import com.samirmaciel.bodybuildingweightregister.view.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingAFragment : Fragment() {

    private var _binding: FragmentTrainingABinding? = null
    private val binding: FragmentTrainingABinding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()
    lateinit var recyclerAdapter: EquipamentModelsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainingABinding.bind(inflater.inflate(R.layout.fragment_training_a, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setObservers()
    }

    private fun setObservers(){
        viewModel.equipamentModelListA.observe(viewLifecycleOwner){
            recyclerAdapter.itemList = it
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView(){
        recyclerAdapter = EquipamentModelsRecyclerAdapter(childFragmentManager, {updatedEquipament ->
            viewModel.insertEquipamentModel(updatedEquipament)
        },{deletedEquipament ->
            viewModel.deleteEquipamentModel(deletedEquipament)})

        binding.rvTrainingA.adapter = recyclerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}