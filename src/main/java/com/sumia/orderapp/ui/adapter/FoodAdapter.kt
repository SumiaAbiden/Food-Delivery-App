package com.sumia.orderapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.sumia.orderapp.R
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.databinding.ProductCardBinding
import com.sumia.orderapp.ui.fragments.MainFragmentDirections
import com.sumia.orderapp.ui.viewmodel.MainViewModel
import com.sumia.orderapp.utils.gecisYap

class FoodAdapter(
    private val mContext: Context,
    private var foodList: List<Food>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<FoodAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(val design: ProductCardBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = ProductCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        Log.d("FoodAdapter", "Yemek adı: ${foodList[position].yemek_adi}")
        val food = foodList.get(position)
        val d = holder.design

        d.textViewName.text = food.yemek_adi
        d.textViewPrice.text = "${food.yemek_fiyat} ₺"



        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
        Log.d("FoodAdapter", "loading photo: $url")

        Glide.with(mContext)
            .load(url)
            .override(300, 300)
            .into(d.imageViewProductCard)

        d.cardViewProduct.setOnClickListener{
            val nav = MainFragmentDirections.detailNav(food = food)
            Navigation.gecisYap(it, nav)
        }

        d.button3.setOnClickListener {
            viewModel.save(food, 1, "sumiaabiden")
            Snackbar.make(it, "${food.yemek_adi} added to cart.", Snackbar.LENGTH_SHORT).show()
        }

    }
}
