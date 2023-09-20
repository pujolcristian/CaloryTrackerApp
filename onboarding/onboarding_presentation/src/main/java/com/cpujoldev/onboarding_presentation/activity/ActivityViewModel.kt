package com.cpujoldev.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpujoldev.core.domain.model.ActivityLevel
import com.cpujoldev.core.domain.preferences.Preferences
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.OnActivityClick -> handleActivityLevelChange(event.activityLevel)

            ActivityEvent.OnNextClick -> handleNextClick()
        }
    }

    private fun handleActivityLevelChange(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }

    private fun handleNextClick() {
        viewModelScope.launch {
            saveActivityLevel(selectedActivityLevel)
            navigateToGoalScreen()
        }
    }

    private fun saveActivityLevel(activityLevel: ActivityLevel) {
        preferences.saveActivityLevel(activityLevel)
    }

    private suspend fun navigateToGoalScreen() {
        _uiEvent.send(
            UiEvent.Navigate(Route.GOAL_SCREEN)
        )
    }
}
