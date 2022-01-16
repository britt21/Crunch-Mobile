package com.example.crunch.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.crunch.db.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.crunch.db.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.crunch.model.RecipeViewModel
import com.example.crunch.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var recipeViewModel: RecipeViewModel

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipid = 0
    private var  dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0



    lateinit var binding : FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentBottomSheetBinding.inflate(inflater)
        recipeViewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)

        binding.mealtypeChipgroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipid = checkedId
        }



        binding.dietTypeChipgroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = checkedId
        }

        binding.applyButton.setOnClickListener {
            recipeViewModel.saveMealAndDataType(
                mealTypeChip,
                mealTypeChipid,
                dietTypeChip,
                dietTypeChipId
            )
val action =
    com.example.crunch.BottomSheetFragmentDirections.actionBottomSheetFragmentToFoodFragment()
            findNavController().navigate(action)
        }

        recipeViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner,{
            value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType

            updateChip(value.selectedMealTypeid, binding.mealtypeChipgroup)
            updateChip(value.selectedDietTypeid, binding.dietTypeChipgroup)
        })

        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup){
        if (chipId != 0){
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            }catch (e: Exception){
                Log.d("BottomSheet", e.message.toString())
            }
        }
    }

}