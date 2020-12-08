package com.razbakov.sandbox.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.razbakov.sandbox.database.AppDatabase
import com.razbakov.sandbox.network.Pinger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val appDatabase: AppDatabase,
    private val pinger: Pinger
) : ViewModel() {

    val healthDataList = appDatabase.healthCheckDao().getAllLive()

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            appDatabase.healthCheckDao().getAll().forEach { healthCheckData ->
                appDatabase.healthCheckDao().update(
                    if (pinger.ping(healthCheckData.url)) {
                        healthCheckData.apply {
                            available = true
                            time = Date()
                        }
                    } else {
                        healthCheckData.apply {
                            available = false
                            time = Date()
                        }
                    }
                )
            }
        }
    }
}
