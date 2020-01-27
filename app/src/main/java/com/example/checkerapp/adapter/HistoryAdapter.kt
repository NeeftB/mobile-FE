package com.example.checkerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checkerapp.R
import com.example.checkerapp.model.StatusHistory
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryAdapter(private val statuses: List<StatusHistory>)
    : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.history_item, parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return statuses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(statuses[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(history: StatusHistory) {
            itemView.tvStatus.text =  context.getString(R.string.status, history.status)
            itemView.tvLastCheckedInDate.text = context.getString(R.string.last_checked_in_date,
                history.lastCheckedInDate)
            if(history.lastCheckedOutDate != null)
                //TODO deze statement oplossen.
            itemView.tvLastCheckedOutDate.text = context.getString(R.string.last_checked_out_date,
                history.lastCheckedOutDate)
            else {
                itemView.tvLastCheckedOutDate.text = context.getString(R.string.last_checked_out_date,
                    "")
            }
            itemView.tvReason.text = context.getString(R.string.reason, history.reason)
        }
    }
}