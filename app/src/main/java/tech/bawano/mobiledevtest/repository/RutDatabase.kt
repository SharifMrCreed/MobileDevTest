package tech.bawano.mobiledevtest.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.bawano.mobiledevtest.models.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var instance: ProductDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ProductDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, ProductDatabase::class.java,
                    "products_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }
}