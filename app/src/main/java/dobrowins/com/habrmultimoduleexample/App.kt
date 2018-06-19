package dobrowins.com.habrmultimoduleexample

import android.app.Application
import dobrowins.com.habrmultimoduleexample.di.AppComponent
import dobrowins.com.habrmultimoduleexample.di.DaggerAppComponent
import dobrowins.com.habrmultimoduleexample.di.LibrariesModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .librariesModule(LibrariesModule())
            .build()
    }

}