package {{module.completeName}}.presentation.activities.main

import base.corelibrary.presentation.main.BaseMainActivity
import com.mikepenz.materialdrawer.Drawer

class MainActivity : BaseMainActivity() {
    override suspend fun onSetupDrawer(): Drawer? = DrawerConfig().setup(this)

    override fun onMenuItemSelect(itemId: Int): Boolean = false
}