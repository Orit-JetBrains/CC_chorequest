package com.chorequest.app

import android.graphics.Color
import com.chorequest.app.model.Chore
import com.chorequest.app.model.ChoreAssignment
import com.chorequest.app.model.Kid
import kotlin.random.Random

class ChoreManager {

    private val kids = listOf(
        Kid(1, "Alex", Color.parseColor("#FF6B6B"), 0),
        Kid(2, "Sam", Color.parseColor("#4ECDC4"), 0),
        Kid(3, "Jordan", Color.parseColor("#FFD93D"), 0)
    )

    private val availableChores = listOf(
        Chore(1, "Clean your room", 10, "🧹"),
        Chore(2, "Do the dishes", 15, "🍽️"),
        Chore(3, "Take out trash", 10, "🗑️"),
        Chore(4, "Feed the pet", 5, "🐕"),
        Chore(5, "Water plants", 5, "🌱"),
        Chore(6, "Fold laundry", 15, "👕"),
        Chore(7, "Set the table", 5, "🍴"),
        Chore(8, "Vacuum floor", 20, "🧽"),
        Chore(9, "Make your bed", 5, "🛏️"),
        Chore(10, "Help with groceries", 10, "🛒"),
        Chore(11, "Clean bathroom", 20, "🚿"),
        Chore(12, "Organize toys", 10, "🧸")
    )

    fun getKids(): List<Kid> = kids

    fun generateDailyChores(): List<ChoreAssignment> {
        val shuffledChores = availableChores.shuffled(Random)
        val assignments = mutableListOf<ChoreAssignment>()

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
