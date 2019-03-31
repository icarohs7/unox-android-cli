@file:JvmName("TopLevel")

package base.corelibrary.domain

import arrow.core.Try
import base.corelibrary.presentation.main.BaseMainActivity

/**
 * Short hand syntax to fetch an instance from
 * the DI container
 */
inline fun <reified T : Any> kget(): T = Injector.get()

/**
 * Helper used to navigate to another
 * navigation destination or action
 */
fun navigate(dest: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
    val options = navOptions ?: androidx.navigation.navOptions {
        anim {
            enter = R.anim.enter_animation
            exit = R.anim.exit_animation
            popEnter = R.anim.enter_animation
            popExit = R.anim.exit_animation
        }
    }
    onActivity<BaseMainActivity> { Try { navController.navigate(dest, args, options) } }
}

/** Show a flashbar snackbar with a yellow gradient background */
fun showWarningFlashBar(
        message: String = "",
        duration: Int = 1500,
        gravity: Flashbar.Gravity = Flashbar.Gravity.TOP,
        context: Activity? = null
) {
    fun messageBuilder(act: Activity) {
        Messages.flashBar(act, message, duration, gravity) { backgroundDrawable(R.drawable.bg_gradient_yellow) }
    }
    context?.let(::messageBuilder) ?: onActivity(::messageBuilder)
}

/** Current orientation of the phone */
val appOrientation: Int
    get() = appCtx.resources.configuration.orientation

/** Whether the phone is on landscape orientation or not */
val isOnLandscapeOrientation: Boolean
    get() = appOrientation == Configuration.ORIENTATION_LANDSCAPE


/**
 * Create a [ColorStateList] from
 * a color resource
 */
fun colorStateListFromRes(@ColorRes colorRes: Int): ColorStateList {
    return base.corelibrary.domain.ColorStateList(appColor(colorRes))
}

/**
 * Create a [ColorStateList] from
 * a color int
 */
@Suppress("FunctionName")
fun ColorStateList(@ColorInt color: Int): ColorStateList {
    return ColorStateList.valueOf(color)
}

/**
 * Build a spanned from multiple parts
 */
fun buildSpanned(vararg parts: CharSequence): Spanned {
    return org.jetbrains.anko.buildSpanned {
        append(*parts)
    }
}
