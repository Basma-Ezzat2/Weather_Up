package com.example.weatherup.ui.alert

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherup.R
import com.example.weatherup.databinding.ActivityAddAlertBinding
import java.text.SimpleDateFormat
import java.util.*

class AddAlert : AppCompatActivity(){
    private lateinit var binding: ActivityAddAlertBinding
    private lateinit var addAlarmViewModel: AddAlarmViewModel
    private lateinit var  calendarDay: Calendar
    private var myHour:Int=0
    private var myMinute:Int=0
    private lateinit var alarmDate :String
    private lateinit var alarmTime :String
    private lateinit var  calendarEnd:Calendar
    private  var periodTimeEnd :Long=111
    private var myMonth:Int=0
    private var myYear:Int=0
    private var myDay:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAlertBinding.inflate(layoutInflater)
        calendarDay= Calendar.getInstance()
        calendarEnd= Calendar.getInstance()
        setContentView(binding.root)
        setContentView(R.layout.activity_add_alert)
        updateUI()

    }
     private fun updateUI() {
        binding.alertTime.setOnClickListener {
            getTime()
        }
        binding.alertDate.setOnClickListener {
            getDate()
        }
        saveClicked()
    }
    private fun getTime() {
        val hour = calendarDay[Calendar.HOUR_OF_DAY]
        val minute = calendarDay[Calendar.MINUTE]
        val mTimePicker= TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                myHour = selectedHour
                myMinute = selectedMinute
                val date = Date()
                date.hours = myHour
                date.minutes = myMinute
                binding.alertTime.text = SimpleDateFormat("HH:mm").format(date)
                alarmTime = SimpleDateFormat("HH:mm").format(date).toString()
                //add day
                calendarEnd.time = date
                calendarEnd.add(Calendar.DATE, 1)
                periodTimeEnd=calendarEnd.timeInMillis

            }, hour, minute, false
        )
        mTimePicker.setTitle("Select Alarm Time")
        mTimePicker.show()
    }

    private fun getDate() {
        val year = calendarDay.get(Calendar.YEAR)
        val month = calendarDay.get(Calendar.MONTH)
        val day = calendarDay.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, { _, yearOfDate, monthOfYear, dayOfMonth ->
                binding.alertDate.text = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
                binding.alertDate.visibility = View.VISIBLE
                alarmDate = "$dayOfMonth/${monthOfYear + 1}/$yearOfDate"
                myMonth = monthOfYear + 1
                myYear = yearOfDate
                myDay = dayOfMonth
            }, year, month, day
        )
        dpd.datePicker.minDate = System.currentTimeMillis() - 1000
        dpd.show()
    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun saveClicked(){/*
        binding.button.setOnClickListener {
            if (binding.alertTime.text.isEmpty() || binding.alertDate.text.isEmpty()) {
                Toast.makeText(this, "Please complete your data before save", Toast.LENGTH_LONG).show()
            }
            else {
                val alertSetTime="$myYear/$myMonth/$myDay/$myHour/$myMinute"
                val alarm= Alarm(
                     alarmDate, alarmTime,alertSetTime, calendarDay.timeInMillis,0,true
                )
                addAlarmViewModel.insertOneAlarm(alarm)
                addAlarmViewModel.returnId().observe(this) {
                    setAlarm(this,
                        it, alarmEvent, myHour, myMinute, myDay,
                        myMonth, myYear , periodTimeEnd,it
                    )
                    addAlarmViewModel.updateIdAlarm(it,it)
                    finish()
                }

            }
        }*/
    }
}