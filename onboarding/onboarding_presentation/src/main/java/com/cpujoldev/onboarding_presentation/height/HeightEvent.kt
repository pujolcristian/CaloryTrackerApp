package com.cpujoldev.onboarding_presentation.height

sealed class HeightEvent {
    data class OnHeightChange(val height: String) : HeightEvent()
    object OnNextClick : HeightEvent()
}
