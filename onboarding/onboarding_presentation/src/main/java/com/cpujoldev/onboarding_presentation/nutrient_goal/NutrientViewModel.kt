package com.cpujoldev.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpujoldev.core.domain.preferences.Preferences
import com.cpujoldev.core.domain.use_case.FilterOutDigits
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.core.util.UiEvent
import com.cpujoldev.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter ->
                state = state.copy(carbsRatio = filterOutDigits(event.carbsRatio))

            is NutrientGoalEvent.OnProteinRatioEnter ->
                state = state.copy(proteinRatio = filterOutDigits(event.proteinRatio))

            is NutrientGoalEvent.OnFatRatioEnter ->
                state = state.copy(fatRatio = filterOutDigits(event.fatRatio))

            is NutrientGoalEvent.OnNextClick -> handleNextClick()
        }
    }

    private fun handleNextClick() {
        val result = validateNutrients(
            state.carbsRatio,
            state.proteinRatio,
            state.fatRatio
        )
        when (result) {
            is ValidateNutrients.Result.Success -> handleSuccess(result)
            is ValidateNutrients.Result.Error -> handleError(result)
        }
    }

    private fun handleSuccess(result: ValidateNutrients.Result.Success) {
        preferences.saveCarbRatio(result.carbsRatio)
        preferences.saveProteinRatio(result.proteinRatio)
        preferences.saveFatRatio(result.fatRatio)

        navigateToNextScreen()
    }

    private fun handleError(result: ValidateNutrients.Result.Error) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.ShowSnackBar(result.message))
        }
    }

    private fun navigateToNextScreen() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW_SCREEN))
        }
    }
}
