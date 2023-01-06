package com.cuyer.rusthub.di

import com.cuyer.rusthub.common.Constants
import com.cuyer.rusthub.data.remote.ItemsApi
import com.cuyer.rusthub.data.remote.ServersApi
import com.cuyer.rusthub.data.remote.repository.ItemsRepositoryImpl
import com.cuyer.rusthub.data.remote.repository.ServersRepositoryImpl
import com.cuyer.rusthub.domain.repository.items.ItemsRepository
import com.cuyer.rusthub.domain.repository.servers.ServersRepository
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemsApi(): ItemsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideServersApi(): ServersApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideItemsRepository(api: ItemsApi): ItemsRepository {
        return ItemsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideServersRepository(api: ServersApi): ServersRepository {
        return ServersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ItemsRepository): GetItemsUseCase {
        return GetItemsUseCase(repository)
    }

}