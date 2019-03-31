package base.corelibrary.domain.extensions

/**
 * Emit a notification with vibration and sounds
 * that when clicked is closed and takes the user
 * to the SplashActivity of the application
 */
fun Messages.defaultVibratingNotification(
        ctx: Context,
        title: String,
        message: String,
        bigMessage: String? = null,
        identifier: Int = 9
) {
    Messages.textNotification(
            context = ctx,
            title = title,
            message = message,
            bigMessage = bigMessage,
            autoClose = true,
            identifier = identifier,
            pendingIntent = ctx.pendingIntentToActivity(AppView.SPLASH)
    ) {
        flags(Notification.DEFAULT_ALL)
    }
}
