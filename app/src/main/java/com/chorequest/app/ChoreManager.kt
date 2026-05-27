package com.chorequest.app

import com.chorequest.app.data.ChoreRepository
import com.chorequest.app.data.HardcodedChoreRepository
import com.chorequest.app.model.Chore
import com.chorequest.app.model.ChoreAssignment
import com.chorequest.app.model.Kid
import kotlin.random.Random

class ChoreManager(private val repository: ChoreRepository = HardcodedChoreRepository()) {

    fun getKids(): List<Kid> = repository.getKids()

    fun generateDailyChores(): List<ChoreAssignment> {
        val availableChores = repository.getAvailableChores()
        val shuffledChores = availableChores.shuffled(Random)
        val assignments = mutableListOf<ChoreAssignment>()
        val kids = repository.getKids()

        // Assign 3-4 chores per kid
        kids.forEachIndexed { index, kid ->
            val startIdx = index * 4
            val choresForKid = shuffledChores.subList(startIdx, minOf(startIdx + 4, shuffledChores.size))
            choresForKid.forEach { chore ->
                assignments.add(ChoreAssignment(kid, chore, false))
            }
        }

        return assignments.shuffled(Random)
    }

    fun completeChore(assignment: ChoreAssignment): ChoreAssignment {
        return assignment.copy(isCompleted = true)
    }
}
