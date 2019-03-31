package base.corelibrary.presentation

import kotlin.reflect.KClass

typealias ActivityClass = KClass<out AppCompatActivity>

object AppView {
    lateinit var SPLASH: ActivityClass
    lateinit var LOGIN: ActivityClass
    lateinit var MAIN: ActivityClass
}

object CoreNavigation {
    fun splashActivity() {
        onActivity { navigateTo(AppView.SPLASH, finishActivity = true) }
    }

    fun loginActivity() {
        onActivity { navigateTo(AppView.LOGIN, finishActivity = true) }
    }

    fun mainActivity() {
        onActivity { navigateTo(AppView.MAIN, finishActivity = true) }
    }
}