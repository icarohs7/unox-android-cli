package base.corelibrary.data

import base.corelibrary.data.entities.User

/**
 * Whether the user has
 * a pin or a hash associated
 * with him
 */
suspend fun User.hasPin(): Boolean {
    return onBackground { pin.isNotBlank() || hash.isNotBlank() }
}

/**
 * Whether there is an authenticated user or not
 */
suspend fun User.isLogged(): Boolean {
    return onBackground { id > 0 }
}
