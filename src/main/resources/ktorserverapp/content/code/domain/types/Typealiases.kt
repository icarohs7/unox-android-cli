package {{module.completeName}}.domain.types

import io.ktor.application.ApplicationCall
import io.ktor.util.pipeline.PipelineContext

typealias EndpointContext = PipelineContext<Unit, ApplicationCall>
typealias EndpointInterceptor = suspend EndpointContext.(Unit) -> Unit