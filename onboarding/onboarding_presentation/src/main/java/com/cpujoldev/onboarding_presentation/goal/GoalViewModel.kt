package com.cpujoldev.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpujoldev.core.domain.model.GoalType
import com.cpujoldev.core.domain.preferences.Preferences
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var selectedGoalType by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: GoalEvent) {
        when (event) {
            is GoalEvent.OnGoalClick -> handleActivityLevelChange(event.goalType)
            GoalEvent.OnNextClick -> handleNextClick()
        }
    }

    private fun handleActivityLevelChange(goalType: GoalType) {
        selectedGoalType = goalType
    }

    private fun handleNextClick() {
        viewModelScope.launch {
            saveGoalType(selectedGoalType)
            navigateToNutrientScreen()
        }
    }

    private fun saveGoalType(goalType: GoalType) {
        preferences.saveGoalType(goalType)
    }

    private suspend fun navigateToNutrientScreen() {
        _uiEvent.send(
            UiEvent.Navigate(Route.NUTRIENT_GOAL_SCREEN)
        )
    }
}
