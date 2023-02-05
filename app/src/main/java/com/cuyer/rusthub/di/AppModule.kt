package com.cuyer.rusthub.di

import android.app.Application
import androidx.room.Room
import com.cuyer.rusthub.common.Constants
import com.cuyer.rusthub.data.local.Converters
import com.cuyer.rusthub.data.local.RustHubDatabase
import com.cuyer.rusthub.data.local.repository.FiltersRepositoryImpl
import com.cuyer.rusthub.data.local.repository.ServersFiltersRepositoryImpl
import com.cuyer.rusthub.data.remote.ItemsApi
import com.cuyer.rusthub.data.remote.ServersApi
import com.cuyer.rusthub.data.remote.repository.ItemsRepositoryImpl
import com.cuyer.rusthub.data.remote.repository.ServersRepositoryImpl
import com.cuyer.rusthub.data.util.GsonParser
import com.cuyer.rusthub.domain.repository.filters.FiltersRepository
import com.cuyer.rusthub.domain.repository.filters.ServersFiltersRepository
import com.cuyer.rusthub.domain.repository.items.ItemsRepository
import com.cuyer.rusthub.domain.repository.servers.ServersRepository
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsFromDbUseCase
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsUseCase
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersAfterRefresh
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersFromDbUseCase
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersUseCase
import com.google.gson.Gson
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
    fun provideRustHubDatabase(app: Application): RustHubDatabase{
        return Room.databaseBuilder(
            app,
            RustHubDatabase::class.java,
            "rust_hub_db"
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ItemsRepository, db: RustHubDatabase): GetItemsUseCase {
        return GetItemsUseCase(repository, db.itemsDao)
    }

    @Provides
    @Singleton
    fun provideGetServersUseCase(repository: ServersRepository, db: RustHubDatabase): GetServersUseCase {
        return GetServersUseCase(repository, db.serversDao)
    }

    @Provides
    @Singleton
    fun provideGetItemsFromDbUseCase(db: RustHubDatabase): GetItemsFromDbUseCase {
        return GetItemsFromDbUseCase(db.itemsDao)
    }

    @Provides
    @Singleton
    fun provideGetServersAfterRefresh(repository: ServersRepository, db: RustHubDatabase): GetServersAfterRefresh {
        return GetServersAfterRefresh(repository, db.serversDao)
    }

    @Provides
    @Singleton
    fun provideFiltersRepository(db: RustHubDatabase): FiltersRepository {
        return FiltersRepositoryImpl(db.filtersDao)
    }

    @Provides
    @Singleton
    fun provideServersFiltersRepository(db: RustHubDatabase): ServersFiltersRepository {
        return ServersFiltersRepositoryImpl(db.serversFiltersDao)
    }

    @Provides
    @Singleton
    fun provideGetServersFromDbUseCase(db: RustHubDatabase): GetServersFromDbUseCase {
        return GetServersFromDbUseCase(db.serversDao)
    }
}