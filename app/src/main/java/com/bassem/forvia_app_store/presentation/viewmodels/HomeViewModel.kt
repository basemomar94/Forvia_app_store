package com.bassem.forvia_app_store.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.data.models.ErrorTypes
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import com.bassem.forvia_app_store.presentation.models.AppsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchAppsUseCase: FetchAppsUseCase) :
    ViewModel() {

    private var _appsScreenState: MutableStateFlow<AppsScreenState> =
        MutableStateFlow(AppsScreenState.Loading)
    val appsScreenState: StateFlow<AppsScreenState> get() = _appsScreenState

    init {
        fetchApps()
    }


     fun fetchApps() = viewModelScope.launch {
        fetchAppsUseCase().collect { state ->
            _appsScreenState.value = when (state) {
                is ApiResult.Fail -> AppsScreenState.Error(state.errorTypes)
                is ApiResult.Loading -> AppsScreenState.Loading
                is ApiResult.Success -> AppsScreenState.Data(state.data)
            }

        }
    }

}

sealed class AppsScreenState {
    data object Loading : AppsScreenState()
    data class Data(val apps: List<AppsUi>) : AppsScreenState()
    data class Error(val types: ErrorTypes?) : AppsScreenState()
}