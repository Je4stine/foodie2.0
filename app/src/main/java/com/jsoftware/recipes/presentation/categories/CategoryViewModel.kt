package com.jsoftware.recipes.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsoftware.recipes.domain.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val mealRepository: MealRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(CategoryState())
    val state: StateFlow<CategoryState> = _state.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                val categoryList = mealRepository.getMealCategories()
                Log.e("CategoryViewModel", "The Categories: $categoryList" )
                _state.value = _state.value.copy(
                    category = categoryList.category,
                    isLoading = false
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false
                )
                Log.e("CategoryViewModelError", "Error fetching categories", e)
            }
        }
    }
}