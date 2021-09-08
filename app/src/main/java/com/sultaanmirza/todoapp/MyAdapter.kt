package com.sultaanmirza.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var todos: ArrayList<Todo>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item, parent, false
        )
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvItem.text = todos[position].task
    }

    override fun getItemCount(): Int = todos.size

    inner class MyViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
        var tvItem: TextView = item.findViewById(R.id.tvItem)
    }
}