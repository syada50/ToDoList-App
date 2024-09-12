package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ListItemBinding

class TaskAdapter (private val tasklist: MutableList<Task>, private val clicklisten:TaskClickLister):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    interface TaskClickLister {
        fun onEditClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    class TaskViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskTittle.text = task.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return tasklist.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasklist[position]
        holder.bind(task)
        holder.binding.editBtn.setOnClickListener {
            clicklisten.onEditClick(position)
        }
        holder.binding.deleteBtn.setOnClickListener {
            clicklisten.onDeleteClick(position)
        }
        holder.binding.checkbox.isChecked = task.isCompleted
        holder.binding.checkbox.setOnCheckedChangeListener{_, isChecked ->
            task.isCompleted= isChecked
        }
    }
}