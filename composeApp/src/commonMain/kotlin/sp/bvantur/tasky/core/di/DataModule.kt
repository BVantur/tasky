package sp.bvantur.tasky.core.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import sp.bvantur.inspektify.ktor.InspektifyKtor
import sp.bvantur.inspektify.ktor.LogLevel
import sp.bvantur.tasky.TaskyBuildKonfig

private const val BASE_URL = "https://tasky.pl-coding.com/"

val dataModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    single<HttpClient> {
        HttpClient(engine = get()) {
            install(ContentNegotiation) {
                json(get())
            }
            defaultRequest {
                url(BASE_URL)
            }
            install(InspektifyKtor) {
                logLevel = LogLevel.All
            }
            install(DefaultRequest) {
                header("x-api-key", TaskyBuildKonfig.API_KEY)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 15_000
            }
        }
    }
}
