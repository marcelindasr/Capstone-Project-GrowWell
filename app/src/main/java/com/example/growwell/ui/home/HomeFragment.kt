// HomeFragment.kt
package com.example.growwell.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growwell.R
import com.example.growwell.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data untuk RecyclerView Imunisasi
        val imagesImmunization = intArrayOf(
            R.drawable.immu1, R.drawable.immu2, R.drawable.immu3,
            R.drawable.immu4, R.drawable.immu5, R.drawable.immu6
        )
        val titlesImmunization = resources.getStringArray(R.array.data_name)
        val descriptionsImmunization = resources.getStringArray(R.array.data_description)

        val immunizationList = mutableListOf<ImmunizationItem>()
        for (i in imagesImmunization.indices) {
            immunizationList.add(ImmunizationItem(imagesImmunization[i], titlesImmunization[i], descriptionsImmunization[i]))
        }

        // Setup RecyclerView Imunisasi
        binding.immunizationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.immunizationRecyclerView.adapter = ImmunizationAdapter(immunizationList)

        // Data untuk RecyclerView Nutrisi
        val imagesNutrition = intArrayOf(
            R.drawable.nutri1, R.drawable.nutri2, R.drawable.nutri3,
            R.drawable.nutri4, R.drawable.nutri5, R.drawable.nutri6,
            R.drawable.nutri7, R.drawable.nutri8, R.drawable.nutri9,
            R.drawable.nutri10
        )
        val titlesNutrition = resources.getStringArray(R.array.nutri_name)
        val descriptionsNutrition = resources.getStringArray(R.array.nutri_description)

        val nutritionList = mutableListOf<NutritionItem>()
        for (i in imagesNutrition.indices) {
            nutritionList.add(NutritionItem(imagesNutrition[i], titlesNutrition[i], descriptionsNutrition[i]))
        }

        // Setup RecyclerView Nutrisi
        binding.nutritionRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.nutritionRecyclerView.adapter = NutritionAdapter(nutritionList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
