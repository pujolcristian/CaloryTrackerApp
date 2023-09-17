package com.cpujoldev.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpujoldev.core.domain.model.Gender
import com.cpujoldev.core.domain.preferences.Preferences
import com.cpujoldev.core.navigation.Route
import com.cpujoldev.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: GenderEvent) {
        when (event) {
            is GenderEvent.OnGenderClick -> selectedGender = event.gender

            GenderEvent.OnNextClick -> viewModelScope.launch {
                preferences.saveGender(selectedGender)
                _uiEvent.send(
                    UiEvent.Navigate(Route.AGE_SCREEN)
                )
            }
        }
    }
}
