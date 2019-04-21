package

{ { module.completeName } }

import base.corelibrary.BaseApplication
import base.corelibrary.presentation.AppView
import com.github.icarohs7.unoxandroidarch.UnoxAndroidArch
import com.github.icarohs7.unoxandroidarch.data.db.buildDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import {{module.completeName}}.presentation.SplashActivity
import {{module.completeName}}.presentation.main.MainActivity

@Suppress("unused")
class AppMain : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        AppView.SPLASH = SplashActivity::class
        AppView.MAIN = MainActivity::class
        UnoxAndroidArch.isDebug = BuildConfig.DEBUG
    }

    override fun onCreateKoinModules(): List<Module> {
        // val database = buildDatabase<AppDatabase>().build()

        return listOf(module {
            //single { database }
        })
    }
}