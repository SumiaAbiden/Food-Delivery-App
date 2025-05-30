package com.sumia.orderapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sumia.orderapp.R
import com.sumia.orderapp.databinding.FragmentDetailBinding
import com.sumia.orderapp.ui.viewmodel.DetailViewModel
import com.sumia.orderapp.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val bundle: DetailFragmentArgs by navArgs()
        val getOrder = bundle.food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${getOrder.yemek_resim_adi}"
        Glide.with(this).load(url).override(300, 300).into(binding.productImageDetail)
        binding.productPriceDetail.text = "${getOrder.yemek_fiyat}Tl"
        binding.productNameDetail.text = "${getOrder.yemek_adi}"
//        binding.textViewAmount.text = getOrder.yemek_siparis_adet

        binding.imageHome.setOnClickListener {
            val action = DetailFragmentDirections.detailMainNav()
            Navigation.gecisYap(it, action)
        }

        binding.imageOrders.setOnClickListener {
            val action = DetailFragmentDirections.detailOrederNav()
            Navigation.gecisYap(it, action)
        }

        binding.buttonDecrease.setOnClickListener {
            val currentAmount = binding.textViewAmount.text.toString().toInt()
            val newAmount = if (currentAmount > 0) currentAmount - 1 else 1
            binding.textViewAmount.text = newAmount.toString()
        }



        binding.buttonIncrease.setOnClickListener {
            val currentAmount = binding.textViewAmount.text.toString().toInt()
            val newAmount = if (currentAmount > 0) currentAmount + 1 else 1
            binding.textViewAmount.text = newAmount.toString()
        }



        binding.buttonCartDetail.setOnClickListener {
            val amount = binding.textViewAmount.text.toString().toIntOrNull() ?: 1
            viewModel.save(getOrder, amount, "sumiaabiden")
            Snackbar.make(it, "$amount ${getOrder.yemek_adi} added to card.", Snackbar.LENGTH_SHORT).show()
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}