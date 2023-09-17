package com.cpujoldev.core.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
    object NavigateUp : UiEvent()

}
