package com.example.weatherup.ui.alert

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherup.R
import com.example.weatherup.data.entity.Alarm


class AlertAdapter(var alerts: ArrayList<Alarm>, var alertViewModel: AlertsViewModel) :
        RecyclerView.Adapter<AlertAdapter.AlarmViewHolder>() {

    fun updateAlerts(newAlert: List<Alarm>) {
        alerts.clear()
        alerts.addAll(newAlert)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = AlarmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.alert_item, parent, false)
    )

    override fun getItemCount() = alerts.size

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(alerts[position])
        holder.itemView.setOnClickListener {
            alertViewModel.onClick(alerts[position])
        }
    }

    fun deleteItem(pos: Int) {
        alertViewModel.notificationID.value=alerts[pos].alarmNewID
        alertViewModel.deleteAlarm(alerts[pos])
        alerts.removeAt(pos)
        notifyItemRemoved(pos)
    }

    class AlarmViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageView_alarm)
        private val date = view.findViewById<TextView>(R.id.date_alarm)
        private val time = view.findViewById<TextView>(R.id.alarm_time)
        fun bind(alarm: Alarm) {
            time.text=" "+alarm.time
            date.text =" "+ alarm.date
        }
    }

}