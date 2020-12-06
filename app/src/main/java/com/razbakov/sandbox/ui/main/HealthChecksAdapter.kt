package com.razbakov.sandbox.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.razbakov.sandbox.R
import com.razbakov.sandbox.database.entity.HealthCheckData
import dagger.hilt.android.qualifiers.ApplicationContext

class HealthChecksAdapter(
    private val context: Context,
    private val list: List<HealthCheckData>,
    private val callback: HealthChecksCallback
) : RecyclerView.Adapter<HealthChecksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.health_check_item, parent, false).let { ViewHolder(it) }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let { data ->
            holder.item.setOnClickListener { callback.onItemClicked(data) }
            holder.name.text = data.name
            holder.url.text = data.url
            holder.date.text = data.time.toString()
            holder.status.setBackgroundColor(context.getColor(if (data.available) {
                android.R.color.holo_green_light
            } else {
                android.R.color.holo_red_light
            }));
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val item: View = view.findViewById(R.id.list_item)
        val name: TextView = view.findViewById(R.id.name)
        val url: TextView = view.findViewById(R.id.url)
        val date: TextView = view.findViewById(R.id.date)
        val status: ImageView = view.findViewById(R.id.status)
    }

    interface HealthChecksCallback {

        fun onItemClicked(item: HealthCheckData)
    }
}
