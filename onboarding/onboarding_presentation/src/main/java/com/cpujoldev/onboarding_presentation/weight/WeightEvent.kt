package com.cpujoldev.onboarding_presentation.weight

sealed class WeightEvent {
    data class OnWeightChange(val weight: String) : WeightEvent()
    object OnNextClick : WeightEvent()
}
