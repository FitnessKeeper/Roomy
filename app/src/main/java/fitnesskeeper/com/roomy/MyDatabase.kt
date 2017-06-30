package fitnesskeeper.com.roomy

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @author stuckj, created on 6/29/17.
 */
@Database(entities = arrayOf(User::class), version = 11, exportSchema = false)
abstract class MyDatabase : RoomDatabase()
{
    abstract fun userDao(): UserDao
}