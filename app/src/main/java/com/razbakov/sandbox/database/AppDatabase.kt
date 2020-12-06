package com.razbakov.sandbox.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.razbakov.sandbox.database.converter.DateConverter
import com.razbakov.sandbox.database.dao.HealthCheckDao
import com.razbakov.sandbox.database.entity.HealthCheckData

@TypeConverters(DateConverter::class)
@Database(entities = [HealthCheckData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun healthCheckDao(): HealthCheckDao
}
