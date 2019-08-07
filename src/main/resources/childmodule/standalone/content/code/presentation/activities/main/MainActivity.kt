package {{module.completeName}}.presentation.activities.main

import androidx.appcompat.widget.Toolbar
import base.corelibrary.presentation.main.BaseMainActivity
import com.mikepenz.materialdrawer.Drawer

class MainActivity : BaseMainActivity() {
    var drawer: Drawer? = null

    override fun setupToolbar(toolbar: Toolbar) {
        super.setupToolbar(toolbar)
        drawer = DrawerConfig().setup(this, toolbar)
    }

    override fun onMenuItemSelect(itemId: Int): Boolean = false
}