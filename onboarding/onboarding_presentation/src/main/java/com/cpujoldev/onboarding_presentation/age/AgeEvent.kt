package com.cpujoldev.onboarding_presentation.age

sealed class AgeEvent {
    data class OnAgeChange(val age: String) : AgeEvent()
    object OnNextClick : AgeEvent()
}
