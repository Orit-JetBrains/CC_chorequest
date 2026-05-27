package com.chorequest.app

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chorequest.app.model.ChoreAssignment
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var choreManager: ChoreManager
    private lateinit var choreAdapter: ChoreAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var shuffleButton: MaterialButton
    private var currentAssignments = mutableListOf<ChoreAssignment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choreManager = ChoreManager()

        recyclerView = findViewById(R.id.choresRecyclerView)
        shuffleButton = findViewById(R.id.shuffleButton)

        setupRecyclerView()
        loadChores()

        shuffleButton.setOnClickListener {
            animateShuffleButton()
            loadChores()
        }
    }

    private fun setupRecyclerView() {
        choreAdapter = ChoreAdapter(currentAssignments) { assignment ->
            onChoreCompleted(assignment)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = choreAdapter
    }

    private fun loadChores() {
        currentAssignments.clear()
        currentAssignments.addAll(choreManager.generateDailyChores())
        choreAdapter.updateAssignments(currentAssignments)
    }

    private fun onChoreCompleted(assignment: ChoreAssignment) {
        val index = currentAssignments.indexOf(assignment)
        if (index != -1) {
            currentAssignments[index] = choreManager.completeChore(assignment)
            choreAdapter.updateAssignments(currentAssignments)

            // Animate the card
            recyclerView.findViewHolderForAdapterPosition(index)?.itemView?.let { view ->
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.1f, 1f).apply {
                    duration = 300
                    start()
                }
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.1f, 1f).apply {
                    duration = 300
                    start()
                }
            }
        }
    }

    private fun animateShuffleButton() {
        ObjectAnimator.ofFloat(shuffleButton, "rotation", 0f, 360f).apply {
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
}
