package com.cpujoldev.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpujoldev.core.R
import com.cpujoldev.core.domain.preferences.Preferences
import com.cpujoldev.core.domain.use_case.FilterOutDigits
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.core.util.UiEvent
import com.cpujoldev.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var age by mutableStateOf("20")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChange -> handleAgeChange(event.age)
            AgeEvent.OnNextClick -> handleNextClick()
        }
    }

    private fun handleAgeChange(age: String) {
        if (age.length <= 3) {
            this.age = filterOutDigits(age)
        }
    }

    private fun handleNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                showInvalidAgeError()
                return@launch
            }
            saveAge(ageNumber)
            navigateToHeightScreen()
        }
    }

    private suspend fun showInvalidAgeError() {
        _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_age_cant_be_empty)))
    }

    private suspend fun navigateToHeightScreen() {
        _uiEvent.send(UiEvent.Navigate(Route.HEIGHT_SCREEN))
    }

    private fun saveAge(age: Int) {
        preferences.saveAge(age)
    }
}
