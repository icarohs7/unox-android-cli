package {{module.completeName}}

import {{module.completeName}}.controller.rootController
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    UnoxCore.foregroundDispatcher = Dispatchers.Default
    UnoxCore.backgroundDispatcher = Dispatchers.IO
    
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        serialization()
    }
    
    install(Routing) {
        rootController()
    }
}

