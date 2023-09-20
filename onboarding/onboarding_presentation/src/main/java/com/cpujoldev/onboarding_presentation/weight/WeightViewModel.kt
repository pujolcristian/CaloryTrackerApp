package com.cpujoldev.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpujoldev.core.R
import com.cpujoldev.core.domain.preferences.Preferences
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.core.util.UiEvent
import com.cpujoldev.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var weight by mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: WeightEvent) {
        when (event) {
            is WeightEvent.OnWeightChange -> handleWeightChange(event.weight)
            WeightEvent.OnNextClick -> handleNextClick()
        }
    }

    private fun handleWeightChange(weight: String) {
        if (weight.length <= 5) {
            this.weight = weight
        }
    }

    private fun handleNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                showInvalidWeightError()
                return@launch
            }
            saveWeight(weightNumber)
            navigateToActivityScreen()
        }
    }

    private suspend fun showInvalidWeightError() {
        _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_weight_cant_be_empty)))
    }

    private suspend fun navigateToActivityScreen() {
        _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY_SCREEN))
    }

    private fun saveWeight(height: Float) {
        preferences.saveWeight(height)
    }
}
