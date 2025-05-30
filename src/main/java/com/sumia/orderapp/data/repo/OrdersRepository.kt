package com.sumia.orderapp.data.repo

import com.sumia.orderapp.data.datasource.OrdersDataSource
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.data.entity.Orders

class OrdersRepository(var ordersDataSource: OrdersDataSource) {

    suspend fun uploadFood(): List<Food> = ordersDataSource.uploadFood()



    suspend fun save(food: Food, amount:Int, kullanici_adi:String){
        ordersDataSource.save(food, amount, kullanici_adi)
    }

    suspend fun delete(order_id:Int, kullaniciAdi: String) = ordersDataSource.delete(order_id, kullaniciAdi)

    suspend fun uploadOrders(kullaniciAdi: String) : List<Orders> = ordersDataSource.uploadOrders(kullaniciAdi)

}