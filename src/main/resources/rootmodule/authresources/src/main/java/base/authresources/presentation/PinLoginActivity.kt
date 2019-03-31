package base.authresources.presentation

abstract class PinLoginActivity(
        private val appVersion: String,
        private val pinLength: Int = 5
) : BaseBindingActivity<ActivityPinLoginBinding>() {
    abstract fun onPinLogin(pin: String)

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        binding.txtVersion.text = appVersion
        toggleLoading(false)
        addOnLoadingListener(this::toggleLoading)
        binding.formPin.build(pinLength, ::onPinLogin)
    }

    private fun toggleLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> binding.stateView.displayLoadingState()
            false -> binding.stateView.hideStates()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_pin_login
    }
}