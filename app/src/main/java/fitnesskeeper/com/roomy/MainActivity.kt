package fitnesskeeper.com.roomy

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

// TODO: LifecycleActivity() doesn't derive from AppCompatActivity so crashy crashy on some API versions we support... :-(
class MainActivity : LifecycleActivity()
{
    @BindView(R.id.idText) lateinit var idText: TextView
    @BindView(R.id.firstNameText) lateinit var firstNameText: TextView
    @BindView(R.id.lastNameText) lateinit var lastNameText: TextView
    @BindView(R.id.emailText) lateinit var emailText: TextView
    @BindView(R.id.createdText) lateinit var createdText: TextView
    @BindView(R.id.lastLocationText) lateinit var lastLocationText: TextView
    @BindView(R.id.uuidText) lateinit var uuidText: TextView

    @BindView(R.id.liveUpdateBtn) lateinit var liveUpdateBtn: Button

    // TODO: This should be in the view model...but I'm tired.
    var user : User? = null

    var liveUpdateViewModel : MainActivityLiveUpdateViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.getUser)
    fun getUser(button: Button)
    {
        liveUpdateBtn.isEnabled = true

        val names = arrayOf("Stucklen", "Pan", "Doe", "McClain")
        val n = names.size
        val i = Math.abs(Random().nextInt() % n)
        val name = names[i]

        Single.just(button)
                .map({ _ -> application.roomy.db.userDao().findByLastName(name) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->

                    this.user = user
                    updateUserView(user)
                })
    }

    @OnClick(R.id.liveUpdateBtn)
    fun liveUpdateButtonPressed(@Suppress("UNUSED_PARAMETER") button: Button)
    {
        if (liveUpdateViewModel == null)
        {
            startLiveUpdate()
        }
        else
        {
            stopLiveUpdate()
        }
    }

    private fun updateUserView(user: User?)
    {
        idText.text = user?.uid.toString()
        firstNameText.text = user?.firstName
        lastNameText.text = user?.lastName
        emailText.text = user?.email
        createdText.text = user?.creationDate.toString()
        lastLocationText.text = user?.lastLocation.toString()
        uuidText.text = user?.uuid
    }

    private fun startLiveUpdate()
    {
        liveUpdateBtn.text = "Stop Live Update"
        liveUpdateViewModel = ViewModelProviders.of(this).get(MainActivityLiveUpdateViewModel::class.java)

        // Start live updating...
        liveUpdateViewModel?.startLiveUpdate(user as User)

        // Observe changes to the query
        liveUpdateViewModel?.userData?.observe(this, Observer({ user ->

            updateUserView(user)
        }))
    }

    private fun stopLiveUpdate()
    {
        liveUpdateBtn.text = "Start Live Update"

        liveUpdateViewModel?.userData?.removeObservers(this)
        liveUpdateViewModel?.stopLiveUpdate()
        liveUpdateViewModel = null
    }
}
