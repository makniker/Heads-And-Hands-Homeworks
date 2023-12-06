package com.example.lesson_03_yermakov.presentation.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_03_yermakov.data.responsemodel.ResponseStates
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import com.example.lesson_03_yermakov.domain.usecases.ProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel() {
    private val _productLiveData = MutableLiveData<ResponseStates<ResponseProduct>>()
    val productLiveData: LiveData<ResponseStates<ResponseProduct>> = _productLiveData

    fun fetchProduct(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _productLiveData.postValue(ResponseStates.Loading())
            try {
                _productLiveData.postValue(
                    ResponseStates.Success(
                        useCase.execute(id)
                    )
                )
            } catch (e: Exception) {
                _productLiveData.postValue(ResponseStates.Failure(e))
            }
        }
    }
}