package lsvapp.kitsu.application

import android.app.Application
import lsvapp.kitsu.application.di.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidContext(applicationContext)
            modules(dataModules)
        }
    }
}