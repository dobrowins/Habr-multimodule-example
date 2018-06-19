package dobrowins.com.habrmultimoduleexample.di

import dagger.Component
import dobrowins.com.habrmultimoduleexample.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        LibrariesModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}