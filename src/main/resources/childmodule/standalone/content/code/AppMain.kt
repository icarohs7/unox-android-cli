package {{module.completeName}}

import base.corelibrary.BaseApplication
import base.corelibrary.presentation.AppView
import base.dataresources.data.db.buildDatabase
import com.github.icarohs7.unoxandroidarch.UnoxAndroidArch
import org.koin.core.module.Module
import org.koin.dsl.module
import {{module.completeName}}.presentation.activities.SplashActivity
import {{module.completeName}}.presentation.activities.main.MainActivity

@Suppress("unused")
class AppMain : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        AppView.SPLASH = SplashActivity::class
        AppView.MAIN = MainActivity::class
        UnoxAndroidArch.isDebug = BuildConfig.DEBUG
    }

    override fun onCreateKoinModules(): List<Module> {
        //val database = buildDatabase<AppDatabase>().build()

        return listOf(module {
            //single { database }
        })
    }
}