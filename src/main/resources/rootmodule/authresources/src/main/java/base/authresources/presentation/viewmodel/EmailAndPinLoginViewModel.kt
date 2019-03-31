package base.authresources.presentation.viewmodel

class EmailAndPinLoginViewModel : ViewModel() {
    private val loginStyleRelay: Relay<LoginStyle> = BehaviorRelay.createDefault(
            LoginStyle.PIN)
    private var lastStyle: LoginStyle = LoginStyle.PIN
    private val lock: Any = object {}

    val loginStyleRx: Flowable<LoginStyle> = loginStyleRelay
            .toFlowable(BackpressureStrategy.LATEST)

    fun toggleLoginStyle() {
        val newStyle = when (lastStyle) {
            LoginStyle.PIN -> LoginStyle.EMAIL
            LoginStyle.EMAIL -> LoginStyle.PIN
        }

        synchronized(lock) { lastStyle = newStyle }
        loginStyleRelay.accept(newStyle)
    }

    enum class LoginStyle {
        PIN, EMAIL;
    }
}