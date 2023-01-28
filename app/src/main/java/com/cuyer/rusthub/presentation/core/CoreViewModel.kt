package com.cuyer.rusthub.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.data.local.entity.FiltersEntity
import com.cuyer.rusthub.domain.model.CraftingItems
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.domain.model.ItemsState
import com.cuyer.rusthub.domain.model.ServersState
import com.cuyer.rusthub.domain.repository.filters.FiltersRepository
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsFromDbUseCase
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsUseCase
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val filtersRepository: FiltersRepository): ViewModel() {

    var scrollPosition: Int = 0


    var filtersList: LiveData<List<FiltersEntity>>


    private var selectedItemPosition: Int = -1
    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
    }
    fun getSelectedItemPosition() = selectedItemPosition

    private val _craftingItemsList = MutableLiveData<List<CraftingItems>>()
    val craftingItemsList: LiveData<List<CraftingItems>>
        get() = _craftingItemsList

    private val _getItemsState = MutableLiveData<ItemsState>()
    val getItemsState: LiveData<ItemsState>
        get() = _getItemsState

    private val _getServersState = MutableLiveData<ServersState>()
    val getServersState: LiveData<ServersState>
        get() = _getServersState

    private val _currentFragmentTag = MutableLiveData<String>()
    val currentFragmentTag: LiveData<String>
        get() = _currentFragmentTag

    private val _currentFragmentName = MutableLiveData<String>()
    val currentFragmentName: LiveData<String>
        get() = _currentFragmentName

    private val _getItemsList = MutableLiveData<List<Items>>()
    val getItemsList: LiveData<List<Items>>
        get() = _getItemsList

    private val _searchValue = MutableLiveData<String>()
    val searchValue: LiveData<String> = _searchValue


    fun setSearchValue(value: String) {
        _searchValue.value = value
    }

    fun setCraftingItemsList(craftingItems: List<CraftingItems>) {
        _craftingItemsList.value = craftingItems
    }

    init {
        runBlocking {
            getServers()
            filtersList = filtersRepository.getAll()
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

    private fun getServers() {
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
