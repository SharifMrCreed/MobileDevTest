package tech.bawano.mobiledevtest.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tech.bawano.mobiledevtest.models.PRODUCT
import tech.bawano.mobiledevtest.models.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: Product)


    @Query("SELECT * FROM $PRODUCT")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM $PRODUCT WHERE id =:id")
    fun getProduct(id:Int): Flow<Product>


}