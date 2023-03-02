package tech.bawano.mobiledevtest.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.bawano.mobiledevtest.models.Product

/** Room will provide an abstraction over the underlying SQLite database that android uses. it provides
 * compile time checks for all our database queries which is pretty neat as before that was one pain
 * point. It unfortunately doesn't cover all null safely concerns so that is something to be wary of
 */
@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    // it is of utmost importance that we have only one instance of the database for obvious reasons
    // Im using the singleton pattern for that and the get instance method synchronizes across threads
    // the migration strategy is defined during migration but ill use fallback to destructive migration
    // to ease development
    companion object {
        private var instance: ProductDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ProductDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, ProductDatabase::class.java,
                    "products_database"
                ).fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }
}