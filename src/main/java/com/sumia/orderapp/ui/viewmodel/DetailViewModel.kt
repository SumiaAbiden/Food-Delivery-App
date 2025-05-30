package com.sumia.orderapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.data.repo.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var ordersRepository: OrdersRepository) : ViewModel() {
    fun save(food: Food, amount: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val currentOrders = ordersRepository.uploadOrders(kullanici_adi)

            val existingOrder = currentOrders.find { it.yemek_adi == food.yemek_adi }

            if (existingOrder != null) {
                ordersRepository.delete(existingOrder.sepet_yemek_id, kullanici_adi)


                val newAmount = existingOrder.yemek_siparis_adet.toInt() + amount
                ordersRepository.save(food, newAmount, kullanici_adi)
            } else {
                ordersRepository.save(food, amount, kullanici_adi)
            }
        }
    }
}