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
import com.google.android.material.snackbar.Snackbar
import com.sumia.orderapp.R
import com.sumia.orderapp.databinding.FragmentOrdersBinding
import com.sumia.orderapp.ui.adapter.OrdersAdapter
import com.sumia.orderapp.ui.viewmodel.MainViewModel
import com.sumia.orderapp.ui.viewmodel.OrdersViewModel
import com.sumia.orderapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val viewModel: OrdersViewModel by viewModels()

    // Adapter'i fragment seviyesinde tanımla
    private lateinit var ordersAdapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        // Adapter'i burada sadece bir kere oluştur
        ordersAdapter = OrdersAdapter(requireContext(), listOf(), viewModel)
        binding.ordersRV.adapter = ordersAdapter
        binding.ordersRV.layoutManager = LinearLayoutManager(requireContext())

        viewModel.ordersList.observe(viewLifecycleOwner) { orders ->
            // Gelen veriyi adapter'a gönder
            ordersAdapter.updateOrders(orders) // adapter içinde listeyi güncelleyen bir fonksiyon olmalı

            // Toplam fiyatı hesapla ve yazdır
            val totalPrice = viewModel.calculateTotalPrice(orders)
            binding.textViewTotal.text = "$totalPrice TL"

            Log.d("OrdersFragment", "Gelen yemek sayısı: ${orders.size}")
        }

        binding.imageHome.setOnClickListener {
            val action = OrdersFragmentDirections.ordersMainNav()
            Navigation.gecisYap(it, action)
        }

        binding.buttonPayment.setOnClickListener {
            Snackbar.make(it, "Payment Completed", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}
