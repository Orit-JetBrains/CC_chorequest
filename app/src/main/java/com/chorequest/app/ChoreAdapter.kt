package com.chorequest.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chorequest.app.model.ChoreAssignment
import com.google.android.material.button.MaterialButton

class ChoreAdapter(
    private var assignments: List<ChoreAssignment>,
    private val onComplete: (ChoreAssignment) -> Unit
) : RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder>() {

    class ChoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val kidColorBar: View = view.findViewById(R.id.kidColorBar)
        val choreEmoji: TextView = view.findViewById(R.id.choreEmoji)
        val kidName: TextView = view.findViewById(R.id.kidName)
        val choreTitle: TextView = view.findViewById(R.id.choreTitle)
        val chorePoints: TextView = view.findViewById(R.id.chorePoints)
        val completeButton: MaterialButton = view.findViewById(R.id.completeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chore, parent, false)
        return ChoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoreViewHolder, position: Int) {
        val assignment = assignments[position]

        holder.kidColorBar.setBackgroundColor(assignment.kid.avatarColor)
        holder.choreEmoji.text = assignment.chore.emoji
        holder.kidName.text = assignment.kid.name
        holder.choreTitle.text = assignment.chore.title
        holder.chorePoints.text = holder.itemView.context.getString(
            R.string.points,
            assignment.chore.points
        )

        if (assignment.isCompleted) {
            holder.completeButton.text = holder.itemView.context.getString(R.string.completed)
            holder.completeButton.isEnabled = false
            holder.itemView.alpha = 0.6f
        } else {
            holder.completeButton.text = holder.itemView.context.getString(R.string.complete_button)
            holder.completeButton.isEnabled = true
            holder.itemView.alpha = 1.0f
            holder.completeButton.setOnClickListener {
                onComplete(assignment)
            }
        }
    }

    override fun getItemCount() = assignments.size

    fun updateAssignments(newAssignments: List<ChoreAssignment>) {
        assignments = newAssignments
        notifyDataSetChanged()
    }
}
