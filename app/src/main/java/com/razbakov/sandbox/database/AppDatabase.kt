package com.razbakov.sandbox.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.razbakov.sandbox.database.converter.DateConverter
import com.razbakov.sandbox.database.dao.HealthCheckDao
import com.razbakov.sandbox.database.entity.HealthCheckData
import com.razbakov.sandbox.network.Pinger
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors

@TypeConverters(DateConverter::class)
@Database(entities = [HealthCheckData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun healthCheckDao(): HealthCheckDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .apply {
                    addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            Timber.d("Database ${db.path} created")
                        }
                    })
                }
                .build()
        }
    }
}
