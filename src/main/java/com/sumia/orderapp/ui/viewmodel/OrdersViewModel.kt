package com.sumia.orderapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.data.entity.Orders
import com.sumia.orderapp.data.repo.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(var ordersRepository: OrdersRepository) : ViewModel(){

    var ordersList = MutableLiveData<List<Orders>>()
    var kullanici_adi:String = "sumiaabiden"

    init{
        uploadOrders(kullanici_adi)
    }

    fun delete(yemek_sepet_id:Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.IO).launch {
            ordersRepository.delete(yemek_sepet_id, kullanici_adi)
            uploadOrders(kullanici_adi)
        }
    }



    fun uploadOrders(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = ordersRepository.uploadOrders(this@OrdersViewModel.kullanici_adi)
                Log.d("OrdersViewModel", "Repo'dan gelen veri: ${result.size}")
                ordersList.value = result
            } catch (e: Exception) {
                Log.e("OrdersViewModel", "uploadFood error: ${e.message}")
            }
        }
    }


    fun calculateTotalPrice(orders: List<Orders>): Int {
        return orders.sumOf { it.yemek_fiyat * it.yemek_siparis_adet.toInt() }
    }




//    fun search(aramaKelimesi:String){
//        CoroutineScope(Dispatchers.Main).launch {
//            try {
//                ordersList.value = ordersRepository.searchOrder(aramaKelimesi)
//            }catch (e: Exception){ }
//        }
//    }

}