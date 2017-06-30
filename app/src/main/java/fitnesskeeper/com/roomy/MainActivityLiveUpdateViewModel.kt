package fitnesskeeper.com.roomy

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author stuckj, created on 6/30/17.
 */
class MainActivityLiveUpdateViewModel(val app: Application): AndroidViewModel(app)
{
    lateinit var userData: LiveData<User>
    var disposables: CompositeDisposable? = null

    fun startLiveUpdate(user: User)
    {
        // THIS IS THE MAGIC
        userData = app.roomy.db.userDao().findById(user.uid)

        disposables = CompositeDisposable()

        // Start inserting new users every second...
        disposables?.add(Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe({ _ ->

                    val localUser = app.roomy.db.userDao().loadAllByIds(listOf(user.uid)).first()
                    localUser?.uuid = UUID.randomUUID().toString()
                    if (localUser != null)
                    {
                        app.roomy.db.userDao().update(localUser)
                    }
                }))
    }

    fun stopLiveUpdate()
    {
        disposables?.dispose()
    }

    override fun onCleared()
    {
        super.onCleared()
        stopLiveUpdate()
    }
}