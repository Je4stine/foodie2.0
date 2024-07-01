package com.jsoftware.recipes.presentation.categoryDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsoftware.recipes.domain.repository.MealRepository
import com.jsoftware.recipes.presentation.home.components.PopularSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private  val mealId: String = checkNotNull(savedStateHandle["mealName"]);
    private val _state = MutableStateFlow(PopularSate())
    val state: StateFlow<PopularSate> = _state.asStateFlow()

    init {
        getCategoryItems()
    }

    private fun getCategoryItems(){
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                val allFoods = mealRepository.getMealsByCategory(mealId)
                _state.value = _state.value.copy(
                    isLoading = false,
                    popularList = allFoods.meals
                )

            }catch (e: Exception){
                _state.value = _state.value.copy(
                    isLoading = false
                )
            }
        }
    }
}