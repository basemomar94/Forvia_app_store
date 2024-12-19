package com.bassem.forvia_app_store.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassem.forvia_app_store.data.models.ApiResult
import com.bassem.forvia_app_store.domain.usecases.FetchAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchAppsUseCase: FetchAppsUseCase) :
    ViewModel() {

    private var _appsList: MutableStateFlow<ApiResult<Any?>?> = MutableStateFlow(null)
    val appsList: MutableStateFlow<ApiResult<Any?>?> get() = _appsList

    init {
        fetchApps()
    }


    private fun fetchApps() = viewModelScope.launch {
        fetchAppsUseCase().collect { apps ->
            _appsList.value = apps
        }
    }

}