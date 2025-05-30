package com.sumia.orderapp.di

import com.sumia.orderapp.data.datasource.OrdersDataSource
import com.sumia.orderapp.data.repo.OrdersRepository
import com.sumia.orderapp.retrofit.ApiUtils
import com.sumia.orderapp.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOrdersRepository(ordersDataSource: OrdersDataSource) : OrdersRepository{
        return OrdersRepository(ordersDataSource)
    }


    @Provides
    @Singleton
    fun provideOrdersDataSource(foodDao: FoodDao) : OrdersDataSource{
        return OrdersDataSource(foodDao)
    }

    @Provides
    @Singleton
    fun provideFoodDao(): FoodDao{
        return ApiUtils.getFoodDao()
    }

}