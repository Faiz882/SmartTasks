package projectConfig

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

data class EnvConfig(
    val apiBaseUrl: String,
)

fun Project.getEnvConfig(file: String): EnvConfig {
    val signingProperties = Properties()
    signingProperties.load(FileInputStream(file(file)))
    return EnvConfig(
        apiBaseUrl = signingProperties.getProperty("apiBaseUrl"),
    )
}

fun Project.getDevEnvConfig(): EnvConfig =
    getEnvConfig("app/env-config/dev-env-config.properties")

fun Project.getStgEnvConfig(): EnvConfig =
    getEnvConfig("app/env-config/stg-env-config.properties")

fun Project.getProdEnvConfig(): EnvConfig =
    getEnvConfig("app/env-config/prod-env-config.properties")

