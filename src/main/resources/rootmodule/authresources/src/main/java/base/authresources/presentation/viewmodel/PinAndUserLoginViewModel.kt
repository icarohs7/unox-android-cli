package base.authresources.presentation.viewmodel

class PinAndUserLoginViewModel : ViewModel() {
    val loginStyleRx: MutableLiveData<LoginStyle> = liveDataOf(
            LoginStyle.PIN)
    private val lastStyle: LoginStyle
        get() = loginStyleRx.valueOr(
                LoginStyle.PIN)

    fun toggleLoginStyle() {
        loginStyleRx.value = when (lastStyle) {
            LoginStyle.PIN -> LoginStyle.USERPASS
            LoginStyle.USERPASS -> LoginStyle.PIN
        }
    }

    enum class LoginStyle {
        PIN, USERPASS;
    }
}