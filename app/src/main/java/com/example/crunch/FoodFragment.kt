package com.example.crunch

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.crunch.databinding.FragmentFoodBinding
import com.example.crunch.foodlist.Result
import com.google.gson.Gson
import com.synnapps.carouselview.CarouselView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList


@AndroidEntryPoint
class FoodFragment() : Fragment(), SearchView.OnQueryTextListener {

    private val args by navArgs<FoodFragmentArgs>()
    private var mAdapter = FoodAdapter()
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var recipieViewModel: RecipeViewModel


    lateinit var binding: FragmentFoodBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(inflater)
        foodViewModel = ViewModelProvider(requireActivity()).get(FoodViewModel::class.java)
        recipieViewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)

        binding.foodFab.setOnClickListener {
            findNavController().navigate(R.id.action_foodFragment_to_bottomSheetFragment)
        }

        readDatabase()
        setUpRecycle()


        mAdapter.onMovieClick {
            val action = FoodFragmentDirections.actionFoodFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            Log.d("FoodFragment", "readDatabase Called")
            foodViewModel.readFood.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty() && !args.backfrombttomsheet) {
                    mAdapter.setData(database[0].foodList)
                    hideShimmerEffect()

                } else {
                    requestData()
                }
            })

        }
    }

    private fun setUpRecycle() {
        binding.rvList.adapter = mAdapter
        showShimmer()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
     return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
      return true
    }

    private fun requestData() {
        Log.d("FoodFragment", "request Data called")
        foodViewModel.getFood(recipieViewModel.applyQueries())
        foodViewModel.livefood.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetWorkResult.Sucess -> {
                    hideShimmerEffect()
                    response.data?.let {
                        mAdapter.setData(it)
                    }
                }
                is NetWorkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetWorkResult.Loading -> {
                    showShimmer()

                }
            }
        })

    }

    fun loadDataFromCache() {
        lifecycleScope.launch {
            foodViewModel.readFood.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodList)
                }
            })
        }

    }

    private fun hideShimmerEffect() {
        binding.rvList.hideShimmer()
    }

    private fun showShimmer() {
        binding.rvList.showShimmer()
    }



}