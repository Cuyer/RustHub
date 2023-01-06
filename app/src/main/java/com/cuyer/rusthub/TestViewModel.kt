package com.cuyer.rusthub

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuyer.rusthub.common.Resource
import com.cuyer.rusthub.domain.use_case.get_items.GetItemsUseCase
import com.cuyer.rusthub.domain.use_case.get_servers.GetServersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getServersUseCase: GetServersUseCase): ViewModel() {

    fun getServers() {
        getServersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("result", "Success: $result")
                }
                is Resource.Error -> {
                    Log.d("result", "Error: ${result.message}")
                }
                is Resource.Loading -> {
                    Log.d("result", "Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getItems() {
        getItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("result", "Success: $result")
                }
                is Resource.Error -> {
                    Log.d("result", "Error: ${result.message}")
                }
                is Resource.Loading -> {
                    Log.d("result", "Loading")
                }
            }
        }.launchIn(viewModelScope)
    }
    }
