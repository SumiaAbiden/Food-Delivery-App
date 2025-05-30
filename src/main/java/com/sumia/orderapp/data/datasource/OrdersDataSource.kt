package com.sumia.orderapp.data.datasource

import android.util.Log
import com.sumia.orderapp.data.entity.CRUDResponse
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.data.entity.Orders
import com.sumia.orderapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrdersDataSource(var foodDao: FoodDao) {
    suspend fun uploadFood() : List<Food> = withContext(Dispatchers.IO){
        val response = foodDao.uploadFood()
        Log.d("OrdersDataSource", "uploadFood response: $response")
        return@withContext response.yemekler
    }


    suspend fun save(food : Food, amount: Int, kullaniciAdi : String) : CRUDResponse = withContext(Dispatchers.IO){
        return@withContext foodDao.save(food.yemek_adi,food.yemek_resim_adi,food.yemek_fiyat,amount,kullaniciAdi)
    }

    suspend fun uploadOrders(kullaniciAdi: String) : List<Orders> = withContext(Dispatchers.IO){
        return@withContext foodDao.uploadOrders(kullaniciAdi).sepet_yemekler

    }

    suspend fun delete(sepetYemekId:Int,kullaniciAdi: String) = withContext(Dispatchers.IO){
        return@withContext foodDao.delete(sepetYemekId,kullaniciAdi)
    }
}