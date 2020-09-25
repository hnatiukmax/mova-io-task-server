package picfinder.server

import picfinder.server.extensions.fromEnvOrNull

internal fun isTestingMode() = IS_TESTING_MODE.fromEnvOrNull()?.toBoolean() ?: false