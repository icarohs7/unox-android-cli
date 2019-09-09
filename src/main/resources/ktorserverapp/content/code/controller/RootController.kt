package {{module.completeName}}.controller

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.rootController() {
    get("/") {
        call.respondText("Ohaio, Sekai! ${call.parameters}", contentType = ContentType.Text.Plain)
    }
}