package base.corelibrary.presentation.main

import arrow.core.Try

open class BaseMaterialDrawerConfig(private val builder: DrawerBuilderKt.() -> Unit = {}) {
    fun setup(activity: BaseMainActivity): Drawer = with(activity) {
        val navDrawer = drawer {
            defaultSettings(this)
            builder(this)
            setupDrawer(this)
        }

        binding.toolbar.setupWithNavController(navController, navDrawer.drawerLayout)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Try { navDrawer.setSelection(destination.id.toLong(), false) }
        }

        return navDrawer
    }


    open fun BaseMainActivity.defaultSettings(builder: DrawerBuilderKt): Unit = with(builder) {
        headerViewRes = R.layout.nav_header
        headerHeightDp = 250
        translucentStatusBar = false

        onItemClick { _, _, item ->
            drawer?.closeDrawer()
            onMenuItemSelect(item.identifier.toInt())
            false
        }
    }

    open fun BaseMainActivity.setupDrawer(builder: DrawerBuilderKt) {}
}
