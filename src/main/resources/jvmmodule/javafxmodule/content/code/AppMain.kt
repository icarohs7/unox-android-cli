package {{module.completeName}}

import {{module.completeName}}.presentation.views.main.MainView
import org.koin.core.context.startKoin
import org.koin.dsl.module
import tornadofx.*

class AppMain : App(MainView::class) {
    init {
        startKoin {
            modules(module {
            })
        }
    }
}