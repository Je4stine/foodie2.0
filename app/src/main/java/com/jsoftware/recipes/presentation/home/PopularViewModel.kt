package com.jsoftware.recipes.presentation.home

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
class PopularViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel(){
    private val _state = MutableStateFlow(PopularSate())
    val state: StateFlow<PopularSate> = _state.asStateFlow()

    init {
        getPopular()
    }

    private fun getPopular(){
        _state.value = _state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                val popularmeals = mealRepository.getPopularMeal()
                _state.value = _state.value.copy(
                    popularList = popularmeals.meals,
                    isLoading = false
                )


            }catch (e: Exception){
                _state.value = _state.value.copy(
                    isLoading = false
                )
            }
        }
    }
}