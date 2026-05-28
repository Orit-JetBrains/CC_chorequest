package com.chorequest.app.data

import android.graphics.Color
import com.chorequest.app.model.Chore
import com.chorequest.app.model.Kid

interface ChoreRepository {
    fun getKids(): List<Kid>
    fun getAvailableChores(): List<Chore>
}

class HardcodedChoreRepository : ChoreRepository {
    override fun getKids(): List<Kid> = listOf(
        Kid(1, "Alex", Color.parseColor("#FF6B6B"), 0),
        Kid(2, "Sam", Color.parseColor("#4ECDC4"), 0),
        Kid(3, "Jordan", Color.parseColor("#FFD93D"), 0)
    )

    override fun getAvailableChores(): List<Chore> = listOf(
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
}
