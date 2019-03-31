package base.corelibrary.domain.extensions

/**
 * Navigate to the giving location popping out all backstack
 * destinations in between
 */
fun NavController.navigatePopping(@IdRes destination: Int, args: Bundle? = null) {
    val navOptions = NavOptions
            .Builder()
            .setPopUpTo(destination, true)
            .build()
    this.navigate(destination, args, navOptions)
}
