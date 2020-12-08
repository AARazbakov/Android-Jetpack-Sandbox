package com.razbakov.sandbox.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.razbakov.sandbox.database.entity.HealthCheckData

@Dao
interface HealthCheckDao {
    @Query("SELECT * FROM sites")
    fun getAllLive(): LiveData<List<HealthCheckData>>

    @Query("SELECT * FROM sites")
    suspend fun getAll(): List<HealthCheckData>

    @Insert
    fun insert(healthCheckEntity: HealthCheckData)

    @Update
    fun update(healthCheckEntity: HealthCheckData)

    @Insert
    fun insertAll(vararg healthCheckEntities: HealthCheckData)

    @Delete
    fun delete(healthCheckEntity: HealthCheckData)
}
