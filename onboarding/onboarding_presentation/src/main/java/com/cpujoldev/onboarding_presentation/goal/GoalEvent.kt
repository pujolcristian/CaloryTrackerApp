package com.cpujoldev.onboarding_presentation.goal

import com.cpujoldev.core.domain.model.GoalType

sealed class GoalEvent {
    data class OnGoalClick(val goalType: GoalType) : GoalEvent()
    object OnNextClick : GoalEvent()
}
