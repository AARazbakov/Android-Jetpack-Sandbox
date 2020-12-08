package com.razbakov.sandbox.ui.main

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.razbakov.sandbox.R
import com.razbakov.sandbox.database.entity.HealthCheckData
import java.util.*
import java.util.concurrent.TimeUnit

class HealthChecksAdapter :
    ListAdapter<HealthCheckData, HealthChecksAdapter.ViewHolder>(DataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.health_check_item, parent, false).let { ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { data ->
            holder.name.text = data.name
            holder.url.text = data.url
            holder.date.text = String.format(
                "Updated %s min ago",
                TimeUnit.MINUTES.convert(Date().time - data.time.time, TimeUnit.MILLISECONDS)
            )
            holder.status.setBackgroundColor(
                Resources.getSystem().getColor(
                    if (data.available) {
                        android.R.color.holo_green_light
                    } else {
                        android.R.color.holo_red_light
                    },
                    null
                )
            )
        }
    }

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val url: TextView = view.findViewById(R.id.url)
        val date: TextView = view.findViewById(R.id.date)
        val status: ImageView = view.findViewById(R.id.status)
    }
}

private class DataDiffCallback : DiffUtil.ItemCallback<HealthCheckData>() {

    override fun areItemsTheSame(oldItem: HealthCheckData, newItem: HealthCheckData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HealthCheckData, newItem: HealthCheckData): Boolean {
        return oldItem == newItem
    }
}
