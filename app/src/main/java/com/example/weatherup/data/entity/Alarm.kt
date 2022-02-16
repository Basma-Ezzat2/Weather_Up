package com.example.weatherup.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(

        val alarmType:String,
        val date:String,
        val time:String,
        val event: Long,
        val timeInDay: Long,
        val periodTimeStart: Boolean,
        val periodTimeEnd:Long,
        val description: String,
        val alertTime:String,
        val alarmLong:Long,
        val alarmNewID:Int,
        val alarmOn:Boolean
)
{
        @PrimaryKey(autoGenerate = true)
        var alarmId: Int = 0
}