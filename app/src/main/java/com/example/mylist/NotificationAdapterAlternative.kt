package com.example.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylist.databinding.NotificaionitemBinding

class NotificationAdapterAlternative (private val items: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<NotificationAdapterAlternative.ViewHolder>() {

    inner class ViewHolder(private val binding: NotificaionitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.textViewNotification.text = item
            binding.buttonNotify.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotificaionitemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}