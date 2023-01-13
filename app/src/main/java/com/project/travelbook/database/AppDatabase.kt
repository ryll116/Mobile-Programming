package com.project.travelbook.database

import androidx.room.Database
import com.project.travelbook.model.ModelDatabase
import androidx.room.RoomDatabase
import com.project.travelbook.database.dao.DatabaseDao

@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}