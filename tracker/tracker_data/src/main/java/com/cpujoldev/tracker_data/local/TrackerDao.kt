package com.cpujoldev.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cpujoldev.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query(
        """
           SELECT * FROM trackedfoodentity
           WHERE dayOfMonth = :dayOfMonth AND month = :month AND year = :year
        """
    )
    fun getFoodsForDate(dayOfMonth: String, month: String, year: String): Flow<List<TrackedFoodEntity>>
}
