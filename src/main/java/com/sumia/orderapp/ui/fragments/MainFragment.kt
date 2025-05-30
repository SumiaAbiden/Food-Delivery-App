package com.sumia.orderapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sumia.orderapp.R
import com.sumia.orderapp.databinding.FragmentMainBinding
import com.sumia.orderapp.ui.adapter.FoodAdapter
import com.sumia.orderapp.ui.adapter.OrdersAdapter
import com.sumia.orderapp.ui.viewmodel.MainViewModel
import com.sumia.orderapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel.foodList.observe(viewLifecycleOwner){
            val foodAdapter = FoodAdapter(requireContext(), it, viewModel)
            binding.productsRV.adapter = foodAdapter
            Log.d("MainFragment", "Gelen yemek sayısı: ${it.size}")
        }

        binding.productsRV.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        binding.imageViewOrders.setOnClickListener {
            val action = MainFragmentDirections.ordersNav()
            Navigation.gecisYap(it, action)
        }


//        binding.searchViewMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextChange(newText: String): Boolean {
//                viewModel.search(newText)
//                return true
//            }
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                viewModel.search(query)
//                return true
//            }
//        })


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.uploadFood()
    }
}
