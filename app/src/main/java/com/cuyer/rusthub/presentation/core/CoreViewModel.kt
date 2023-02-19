package com.cuyer.rusthub.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity
import com.cuyer.rusthub.domain.model.*
import com.cuyer.rusthub.domain.repository.filters.FiltersRepository
import com.cuyer.rusthub.domain.repository.filters.ServersFiltersRepository
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsFromDbUseCase
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsUseCase
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersAfterRefresh
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersFromDbUseCase
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getServersUseCase: GetServersUseCase,
    private val getItemsFromDbUseCase: GetItemsFromDbUseCase,
    private val getServersAfterRefresh: GetServersAfterRefresh,
    private val filtersRepository: FiltersRepository,
    private val serversFiltersRepository: ServersFiltersRepository,
    private val getServersFromDbUseCase: GetServersFromDbUseCase): ViewModel() {

    var scrollPosition: Int = 0


    var filtersList: LiveData<List<FiltersEntity>>
    var serversFiltersList: LiveData<List<ServersFiltersEntity>>

    private var selectedItemPosition: Int = -1
    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
    }
    fun getSelectedItemPosition() = selectedItemPosition

    private var raidSelectedItemPosition: Int = -1
    fun setRaidSelectedItemPosition(position: Int) {
        raidSelectedItemPosition = position
    }
    fun getRaidSelectedItemPosition() = raidSelectedItemPosition

    private var raidInitialFilteredList = emptyList<Items>()
    fun setRaidInitialFilteredList(list: List<Items>) {
        raidInitialFilteredList = list
    }

    fun getRaidInitialFilteredList() = raidInitialFilteredList


    private var selectorPosition: Int = 0
    fun setSelectorPosition(position: Int) {
        selectorPosition = position
    }
    fun getSelectorPosition() = selectorPosition

    private val _craftingItemsList = MutableLiveData<List<CraftingItems>>()
    val craftingItemsList: LiveData<List<CraftingItems>>
        get() = _craftingItemsList

    private val _getItemsState = MutableLiveData<ItemsState>()
    val getItemsState: LiveData<ItemsState>
        get() = _getItemsState

    private val _getServersState = MutableLiveData<ServersState>()
    val getServersState: LiveData<ServersState>
        get() = _getServersState

    private val _getServersRefreshState = MutableLiveData<ServersRefreshState>()
    val getServersRefreshState: LiveData<ServersRefreshState>
        get() = _getServersRefreshState

    private val _currentFragmentTag = MutableLiveData<String>()
    val currentFragmentTag: LiveData<String>
        get() = _currentFragmentTag

    private val _currentFragmentName = MutableLiveData<String>()
    val currentFragmentName: LiveData<String>
        get() = _currentFragmentName

    private val _getItemsList = MutableLiveData<List<Items>>()
    val getItemsList: LiveData<List<Items>>
        get() = _getItemsList

    private val _getServersList = MutableLiveData<List<Servers>>()
    val getServersList: LiveData<List<Servers>>
        get() = _getServersList

    private val _searchValue = MutableLiveData<String>()
    val searchValue: LiveData<String> = _searchValue

    private val _serversSearchValue = MutableLiveData<String>()
    val serversSearchValue: LiveData<String> = _serversSearchValue

    private val _scrapSearchValue = MutableLiveData<String>()
    val scrapSearchValue: LiveData<String> = _scrapSearchValue

    fun setSearchValue(value: String) {
        _searchValue.value = value
    }

    fun setServersSearchValue(value: String) {
        _serversSearchValue.value = value
    }

    fun setScrapSearchValue(value: String) {
        _scrapSearchValue.value = value
    }

    fun setCraftingItemsList(craftingItems: List<CraftingItems>) {
        _craftingItemsList.value = craftingItems
    }

    init {
        runBlocking {
            getServers()
            filtersList = filtersRepository.getAll()
            serversFiltersList = serversFiltersRepository.getAll()
        }
    }

    fun setFilter(filter: FiltersEntity) {
        viewModelScope.launch {
            filtersRepository.delete()
            filtersRepository.insert(filter)
        }
    }


    fun deleteFilter() {
        viewModelScope.launch {
            filtersRepository.delete()
        }
    }

    fun setServersFilter(filter: ServersFiltersEntity) {
        viewModelScope.launch {
            serversFiltersRepository.delete()
            serversFiltersRepository.insert(filter)
        }
    }


    fun deleteServersFilter() {
        viewModelScope.launch {
            serversFiltersRepository.delete()
        }
    }

    fun setCurrentFragmentTag(tag: String) {
        _currentFragmentTag.value = tag
    }

    fun setCurrentFragmentName(name: String) {
        _currentFragmentName.value = name
    }

    fun getItemsFromDb() {
        getItemsFromDbUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getItemsList.value = result.data ?: emptyList()
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getServersFromDb() {
        getServersFromDbUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getServersList.value = result.data ?: emptyList()
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getServersAfterRefresh() {
        getServersAfterRefresh.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getServersRefreshState.value = ServersRefreshState(
                        isLoading = false,
                        servers = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _getServersRefreshState.value = ServersRefreshState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _getServersRefreshState.value = ServersRefreshState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

     fun getServers() {
        getServersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getServersState.value = ServersState(
                        isLoading = false,
                        servers = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _getServersState.value = ServersState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _getServersState.value = ServersState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope).invokeOnCompletion { getItems() }
    }

    private fun getItems() {
        getItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getItemsState.value = ItemsState(
                        isLoading = false,
                        items = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _getItemsState.value = ItemsState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _getItemsState.value = ItemsState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    }
