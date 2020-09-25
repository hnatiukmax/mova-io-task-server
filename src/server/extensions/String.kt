package picfinder.server.extensions

internal fun String.fromEnv() = System.getenv(this)
    ?: throw IllegalArgumentException("$this environment variable is missed")

internal fun String.fromEnvOrNull() = System.getenv(this)