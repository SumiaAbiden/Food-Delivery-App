package com.sumia.orderapp.retrofit

import com.sumia.orderapp.data.entity.CRUDResponse
import com.sumia.orderapp.data.entity.Food
import com.sumia.orderapp.data.entity.FoodResponse
import com.sumia.orderapp.data.entity.OrdersResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FoodDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun uploadFood(): FoodResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun save(@Field("yemek_adi") yemek_adi:String,
                     @Field("yemek_resim_adi") yemek_resim_adi:String,
                     @Field("yemek_fiyat") yemek_fiyat:Int,
                     @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                     @Field("kullanici_adi") kullanici_adi:String) : CRUDResponse

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun uploadOrders(
        @Field("kullanici_adi") kullanici_adi: String
    ) : OrdersResponse


    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun delete(@Field("sepet_yemek_id") sepetYemekId:Int,
                       @Field("kullanici_adi") kullaniciAdi:String) : CRUDResponse

}