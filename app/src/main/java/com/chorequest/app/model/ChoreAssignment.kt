package com.chorequest.app.model

data class ChoreAssignment(
    val kid: Kid,
    val chore: Chore,
    val isCompleted: Boolean = false
)
