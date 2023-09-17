package com.cpujoldev.onboarding_presentation.gender

import com.cpujoldev.core.domain.model.Gender

sealed class GenderEvent {
    data class OnGenderClick(val gender: Gender) : GenderEvent()
    object OnNextClick : GenderEvent()
}
