package {{module.completeName}}

import com.github.icarohs7.unoxcore.UnoxCore
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import kotlinx.coroutines.Dispatchers
import io.ktor.server.jetty.EngineMain as JettyEngine

fun main(args: Array<String>): Unit = JettyEngine.main(args)

@Suppress("unused")
fun Application.module() {
    UnoxCore.foregroundDispatcher = Dispatchers.Default
    UnoxCore.backgroundDispatcher = Dispatchers.IO
    
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        serialization()
    }

    routing {
    }
}

