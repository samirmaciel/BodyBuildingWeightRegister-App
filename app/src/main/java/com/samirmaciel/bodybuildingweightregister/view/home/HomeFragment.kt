package com.samirmaciel.bodybuildingweightregister.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.samirmaciel.bodybuildingweightregister.R
import com.samirmaciel.bodybuildingweightregister.databinding.FragmentHomeBinding
import com.samirmaciel.bodybuildingweightregister.view.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        loadTabConfiguration()
    }

    private fun setListeners(){

        binding.fabAdd.setOnClickListener{
            val addEquipamentBottomSheet = AddEquipamentBottomSheet(isEdit = false, null){
                viewModel.insertEquipamentModel(it)
            }

            addEquipamentBottomSheet.show(childFragmentManager, "AddEquipamentBottomSheet")
        }

        binding.btnA.setOnClickListener {
            TabConfiguration.goTo(TabScreen.TRAINING_A)
                ?.let { it1 -> requireActivity().findNavController(R.id.home_container).navigate(it1) }
        }
        binding.btnB.setOnClickListener {
            TabConfiguration.goTo(TabScreen.TRAINING_B)
                ?.let { it1 -> requireActivity().findNavController(R.id.home_container).navigate(it1) }
        }
        binding.btnC.setOnClickListener {
            TabConfiguration.goTo(TabScreen.TRAINING_C)
                ?.let { it1 -> requireActivity().findNavController(R.id.home_container).navigate(it1) }
        }
    }
    private fun setObservers(){

        viewModel.onResultMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadTabConfiguration(){
        TabConfiguration.btnTrainingA = binding.btnA
        TabConfiguration.btnTrainingB = binding.btnB
        TabConfiguration.btnTrainingC = binding.btnC

        TabConfiguration.currentTab = TabScreen.TRAINING_A
        TabConfiguration.lastTab = TabScreen.TRAINING_A

        TabConfiguration.colorActive = resources.getColor(R.color.teal_200)
        TabConfiguration.colorUnActive = resources.getColor(R.color.gray)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}