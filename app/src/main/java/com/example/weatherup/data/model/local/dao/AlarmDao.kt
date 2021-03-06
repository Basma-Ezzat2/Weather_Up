package com.example.weatherup.data.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherup.data.entity.Alarm

@Dao
interface AlarmDao {

  @Query("SELECT * FROM Alarm")
  fun getAllAlarms(): LiveData<List<Alarm>>

  @Query("DELETE FROM Alarm WHERE alarmId LIKE :alarmId")
  fun deleteById(alarmId: Int)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAlarm(alarm: Alarm):Long

  @Query("UPDATE Alarm SET alarmOn = :p1_1702747034  WHERE alarmId LIKE :id ")
  fun updateItem(id: Int, p1_1702747034: Boolean)

  @Query("UPDATE Alarm SET alarmNewID = :newId  WHERE alarmId LIKE :id ")
  fun updateIdAlarm(id: Int, newId: Int)

  @Delete
  fun delete(alarm: Alarm)
}

