package com.innocent.crop2cashassessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innocent.crop2cashassessment.model.Exhibit
import com.innocent.crop2cashassessment.model.ExhibitsLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExhibitViewModel  @Inject constructor(private val exhibitsLoader: ExhibitsLoader): ViewModel() {

    private val _exhibits = MutableLiveData<List<Exhibit>>()
    val exhibits: LiveData<List<Exhibit>> = _exhibits

    private val _viewState = MutableLiveData<ExhibitListViewState>(
        ExhibitListViewState.Success(
            emptyList()
        )
    )
    val viewState: LiveData<ExhibitListViewState> = _viewState

    init {
        loadExhibits()
    }

    private fun loadExhibits(){
        _viewState.value = ExhibitListViewState.Loading

        viewModelScope.launch {
            runCatching {
                _exhibits.value = exhibitsLoader.getExhibitList()
                exhibitsLoader.getExhibitList()
            }.onSuccess { collection ->
                _viewState.value = ExhibitListViewState.Success(collection)
            }
        }
    }
}

sealed class ExhibitListViewState {
    object Loading : ExhibitListViewState()
    object Error : ExhibitListViewState()
    data class Success(val collection: List<Exhibit>) : ExhibitListViewState()
}