package com.example.lesson_03_yermakov.presentation.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_03_yermakov.data.responsemodel.ResponseStates
import com.example.lesson_03_yermakov.domain.usecases.GetCatalogUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(private val getCatalogUseCase: GetCatalogUseCase) :
    ViewModel() {

    private val _catalogLiveData = MutableLiveData<ResponseStates<MutableList<UIModelCatalogProduct>>>()
    val catalogLiveData: LiveData<ResponseStates<MutableList<UIModelCatalogProduct>>> = _catalogLiveData
    fun fetchCatalog() {
        viewModelScope.launch {
            _catalogLiveData.value = ResponseStates.Loading()
            try {
                _catalogLiveData.value = ResponseStates.Success(
                    getCatalogUseCase.execute()
                )
            } catch (e: Exception) {
                _catalogLiveData.value = ResponseStates.Failure(e)
            }
        }
    }
}