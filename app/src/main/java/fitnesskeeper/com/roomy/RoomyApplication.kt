package fitnesskeeper.com.roomy

import android.app.Application
import android.arch.persistence.room.Room
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author stuckj, created on 6/30/17.
 */
class RoomyApplication: Application()
{
    lateinit var db: MyDatabase
    private set

    override fun onCreate()
    {
        super.onCreate()

        db = Room.databaseBuilder(this, MyDatabase::class.java, "roomy").build()

        // Start with a clean slate. Clear all users
        Single.just(1)
                .observeOn(Schedulers.io())
                .subscribe({ _ ->

                    db.userDao().deleteAllUsers()

                    // Add a couple users
                    db.userDao().insertAll(User(UUID.randomUUID().toString(), "John", "Stucklen", "john.stucklen@runkeeper.com", Date(), MyLocation(42.1, -72.6)),
                            User(UUID.randomUUID().toString(), "Peter", "Pan", "peter.pan@runkeeper.com", Date(), MyLocation(45.2, -70.0)),
                            User(UUID.randomUUID().toString(), "John", "Doe", "john.doe@runkeeper.com", Date(), MyLocation(32.6, -110.8)),
                            User(UUID.randomUUID().toString(), "John", "McClain", "john.mcclain@runkeeper.com", Date(), MyLocation(-60.3, 56.9)))
                })
    }
}

val Application.roomy get() = this as RoomyApplication