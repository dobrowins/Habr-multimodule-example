package dobrowins.com.habrmultimoduleexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dobrowins.com.randomproviderlibrary.RandomNumberProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var randomNumberProvider: RandomNumberProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomNumber = randomNumberProvider.provideRandomNumber(1024)
        val message = String.format(getString(R.string.initial_message), randomNumber)
        tvMainActivityMessage.text = message
    }

}
