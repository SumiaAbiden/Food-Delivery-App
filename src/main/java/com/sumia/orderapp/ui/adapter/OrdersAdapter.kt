package com.sumia.orderapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.data.entity.Orders
import com.sumia.orderapp.data.repo.OrdersRepository
import com.sumia.orderapp.databinding.OrderCardBinding
import com.sumia.orderapp.databinding.ProductCardBinding
import com.sumia.orderapp.ui.fragments.MainFragmentDirections
import com.sumia.orderapp.ui.fragments.OrdersFragmentDirections
import com.sumia.orderapp.ui.viewmodel.MainViewModel
import com.sumia.orderapp.ui.viewmodel.OrdersViewModel
import com.sumia.orderapp.utils.gecisYap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersAdapter(var mContext: Context,
                    var ordersList: List<Orders>,
                    var viewModel: OrdersViewModel)
        :RecyclerView.Adapter<OrdersAdapter.CardDesignHolder>(){

            inner class CardDesignHolder(var design:OrderCardBinding):RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = OrderCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val order = ordersList.get(position)
        val d = holder.design
        d.textViewNameOrder.text = order.yemek_adi
        d.textViewPriceOrder.text = "${order.yemek_fiyat} ₺"
        d.textViewAmountCard.text = "Amount: ${order.yemek_siparis_adet}"

        d.imageViewdelete.setOnClickListener {
            viewModel.delete(order.sepet_yemek_id, order.kullanici_adi)
        }


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${order.yemek_resim_adi}"
        Glide.with(mContext)
            .load(url)
            .override(300, 300)
            .into(d.imageViewProductOrder)


    }

    fun updateOrders(newOrders: List<Orders>) {
        this.ordersList = newOrders
        notifyDataSetChanged()
    }
}

