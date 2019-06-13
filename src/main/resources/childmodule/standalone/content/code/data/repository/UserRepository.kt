package {{module.completeName}}.data.repository

import arrow.core.Try
import base.corelibrary.data.entities.User
import base.corelibrary.presentation.CoreNavigation
import com.github.icarohs7.unoxcore.tryBg

class UserRepository {
    suspend fun logout(): Try<Unit> {
        return tryBg {
            User.clear()
            CoreNavigation.loginActivity()
        }
    }
}