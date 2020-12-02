
package com.bankin.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bankin.data.model.ResourceEntity


/**
 * Data Access Object for the UserProductCart entity
 */
@Dao
interface ResourceRepoDao {
    /**
     * Select all cart from the TrendingRepoEntity.
     *
     * @return all products in cart.
     */
    @Query("SELECT * FROM Resource")
    suspend fun allCategories(): List<ResourceEntity>

    /**
     * Insert all repose
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(userProductCart: List<ResourceEntity?>?)

    /**
     * Delete all repos
     */
    @Query("DELETE FROM Resource")
    suspend fun deleteAllCategories()

    /**
     * Delete all repos
     */
    @Query("SELECT COUNT(*) FROM Resource")
    suspend fun isCategoriesCacheAvailable(): Int
}