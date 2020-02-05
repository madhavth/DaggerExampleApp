package com.madhavth.daggermvvmapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhavth.daggermvvmapp.R
import com.madhavth.daggermvvmapp.data.models.Todos
import kotlinx.android.synthetic.main.item_todo_list.view.*

class TodoListRecyclerViewAdapter : ListAdapter<Todos, TodoListRecyclerViewAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoListRecyclerViewAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Todos) = with(itemView) {
            tvTodoItem.text = item.title + "  ${item.completed}"
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Todos>() {
    override fun areItemsTheSame(oldItem: Todos, newItem: Todos): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todos, newItem: Todos): Boolean {
        return oldItem == newItem
    }
}