package {{module.completeName}}

import {{module.completeName}}.presentation.views.main.MainView
import com.github.icarohs7.unoxcore.UnoxCore
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.module
import tornadofx.*

class AppMain : App(MainView::class) {
    init {
        UnoxCore.foregroundDispatcher = Dispatchers.Main
        startKoin {
            modules(module {
            })
        }
    }
}