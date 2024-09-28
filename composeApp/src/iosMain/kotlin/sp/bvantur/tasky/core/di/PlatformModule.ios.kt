package sp.bvantur.tasky.core.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single {
        Darwin.create()
    }
}
