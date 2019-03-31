package base.corelibrary.presentation.main

import arrow.core.Try
import arrow.core.getOrElse
import base.corelibrary.data.entities.User
import base.corelibrary.domain.navigate

abstract class BaseMainActivity(
        drawerConfig: BaseMaterialDrawerConfig? = BaseMaterialDrawerConfig()
) : BaseBindingActivity<ActivityBaseMainBinding>() {
    @Suppress("MemberVisibilityCanBePrivate")
    val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }
    val drawer: Drawer? by lazy { drawerConfig?.setup(this) }

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        addOnLoadingListener(this::toggleLoading)
        setSupportActionBar(binding.toolbar)
        setupNavigation()
        title = navController.currentDestination?.label
        launch { showUserNameOnNavDrawer() }
    }

    private fun setupNavigation() {
        binding.apply {
            drawer
            bottomNav.setupWithNavController(navController)
            bottomNav.setOnNavigationItemSelectedListener(::onOptionsItemSelected)
        }
    }

    private suspend fun showUserNameOnNavDrawer() {
        val userName = onBackground { User.name }
        showNavHeaderNameWhen(userName.isNotBlank(), userName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item) || onMenuItemSelect(item.itemId)
    }

    open fun onMenuItemSelect(itemId: Int): Boolean = when (itemId) {
        R.id.menu_logout -> logout().let { true }
        R.id.menu_refresh -> onRefresh().let { true }
        else -> Try { navigate(itemId).let { true } }.getOrElse { false }
    }

    fun logout() {
        showConfirmDialog("Confirmar", "Deseja se desconectar?") { dialog ->
            btnYesDialogyesno.text = getString(R.string.sim)
            btnNoDialogyesno.text = getString(R.string.nao)
            txtTitleDialogyesno.backgroundColor = color(R.color.colorAccent)
            setYesHandler {
                launch { onLogout() }
                dialog.dismiss()
            }
        }
    }

    open suspend fun onLogout() {
    }

    open fun onRefresh() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_main
    }
}
