package com.razbakov.sandbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.razbakov.sandbox.database.entity.HealthCheckData

@Dao
interface HealthCheckDao {
    @Query("SELECT * FROM sites")
    fun getAll(): List<HealthCheckData>

    @Insert
    fun insertAll(vararg healthCheckEntities: HealthCheckData)

    @Delete
    fun delete(healthCheckEntity: HealthCheckData)
}
