package base.authresources.presentation

import base.authresources.presentation.viewmodel.PinAndUserLoginViewModel

abstract class PinAndUserLoginActivity(
        private val appVersion: String,
        private val pinLength: Int = 5
) : BaseBindingActivity<ActivityPinAndUserLoginBinding>() {
    val viewModel: PinAndUserLoginViewModel by lazy {
        ViewModelProviders.of(this).get(PinAndUserLoginViewModel::class.java)
    }

    val pinBinding: PartialPinFormBinding by lazy { PartialPinFormBinding.inflate(layoutInflater) }
    val userFormBinding: PartialUserPasswordFormBinding by lazy {
        PartialUserPasswordFormBinding.inflate(layoutInflater)
    }

    /**
     * Used to dispatch the login call event and trigger either
     * the [onPinLogin] or the [onUserPassLogin] operations
     * @param loginCallable Function holding the login handler
     */
    open suspend fun onBeforeLogin(loginCallable: suspend () -> Unit) {
        hideKeyboard(binding.root)
        loginCallable()
    }

    abstract suspend fun onPinLogin(pin: String)
    abstract suspend fun onUserPassLogin(user: String, password: String)
    abstract suspend fun afterLogin()

    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        binding.txtVersion.text = appVersion
        toggleLoading(false)
        addOnLoadingListener(this::toggleLoading)
        setupBinding()
        observeLoginStyle()
    }

    private fun setupBinding() {
        binding.btnSwitchLoginMode.setOnClickListener { viewModel.toggleLoginStyle() }
        userFormBinding.build { user, pass ->
            launch {
                onBeforeLogin {
                    startLoading()
                    onUserPassLogin(user, pass)
                    stopLoading()
                    afterLogin()
                }
            }
        }

        pinBinding.build(pinLength) { pin ->
            hideKeyboard(binding.root)
            launch {
                onBeforeLogin {
                    startLoading()
                    onPinLogin(pin)
                    stopLoading()
                    afterLogin()
                }
            }
        }
    }


    private fun observeLoginStyle() {
        viewModel
                .loginStyleRx
                .observe(this) { style: PinAndUserLoginViewModel.LoginStyle ->
                    when (style) {
                        PinAndUserLoginViewModel.LoginStyle.PIN -> showPinLogin()
                        PinAndUserLoginViewModel.LoginStyle.USERPASS -> showUserPassLogin()
                    }
                }
    }

    private fun showPinLogin() {
        changeFormContent(pinBinding.root, "Utilizar\nlogin e senha")
    }

    private fun showUserPassLogin() {
        changeFormContent(userFormBinding.root, "Utilizar\nPIN")
    }

    private fun changeFormContent(content: View, switchButtonText: String) {
        binding.apply {
            layoutFormContent.animateFadeOut(50L) {
                layoutFormContent.removeAllViews()
                layoutFormContent += content
                layoutFormContent.animateFadeIn(50L)
                btnSwitchLoginMode.text = switchButtonText
            }
        }
    }

    private fun toggleLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> binding.stateView.displayLoadingState()
            false -> binding.stateView.hideStates()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_pin_and_user_login
    }
}