package dobrowins.com.habrmultimoduleexample.di

import dagger.Module
import dagger.Provides
import dobrowins.com.randomproviderlibrary.RandomNumberProvider
import dobrowins.com.randomproviderlibrary.RandomNumberProviderImpl
import javax.inject.Singleton

@Module
class LibrariesModule {

    @Provides
    @Singleton
    fun randomNumberProvider(): RandomNumberProvider =
        RandomNumberProviderImpl()

}