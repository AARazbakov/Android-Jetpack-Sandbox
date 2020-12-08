package com.razbakov.sandbox

import android.app.Application
import com.razbakov.sandbox.database.AppDatabase
import com.razbakov.sandbox.database.entity.HealthCheckData
import com.razbakov.sandbox.network.Pinger
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.*
import javax.inject.Inject


@HiltAndroidApp
class SandboxApp : Application() {

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var pinger: Pinger

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        GlobalScope.launch {
            appDatabase.healthCheckDao().getAll().ifEmpty {
                appDatabase.healthCheckDao().insert(
                    HealthCheckData(
                        id = 1,
                        name = "Yandex",
                        url = "yandex.ru",
                        time = Date(),
                        available = pinger.ping("yandex.ru")
                    )
                )
            }
        }
    }
}
