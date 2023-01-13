package com.project.travelbook.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import com.project.travelbook.model.ModelDatabase
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM travel_db")
    fun getAllHistory(): LiveData<List<ModelDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(vararg modelDatabases: ModelDatabase)

    @Query("DELETE FROM travel_db WHERE uid= :uid")
    fun deleteHistoryById(uid: Int)

    @Query("DELETE FROM travel_db")
    fun deleteAllHistory()
}
