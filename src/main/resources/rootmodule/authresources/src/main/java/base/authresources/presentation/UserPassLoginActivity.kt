package base.authresources.presentation

abstract class UserPassLoginActivity(appVersion: String) : PinAndUserLoginActivity(appVersion) {
    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        binding.btnSwitchLoginMode.isInvisible = true
    }

    override suspend fun onPinLogin(pin: String) {
    }
}