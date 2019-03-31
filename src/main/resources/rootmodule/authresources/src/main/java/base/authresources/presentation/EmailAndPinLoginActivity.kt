package base.authresources.presentation

import base.authresources.presentation.viewmodel.EmailAndPinLoginViewModel

abstract class EmailAndPinLoginActivity(
        private val appVersion: String,
        private val pinLength: Int = 5
) : BaseBindingActivity<ActivityEmailAndPinLoginBinding>() {
    private val viewModel: EmailAndPinLoginViewModel by lazy {
        ViewModelProviders.of(this).get(EmailAndPinLoginViewModel::class.java)
    }

    val pinBinding: PartialPinFormBinding by lazy { PartialPinFormBinding.inflate(layoutInflater) }
    val emailFormBinding: PartialEmailFormBinding by lazy {
        PartialEmailFormBinding.inflate(layoutInflater)
    }

    /**
     * Used to dispatch the login call event and trigger either
     * the [onPinLogin] or the [onEmailLogin] operations
     * @param loginCallable Function holding the login handler
     */
    open suspend fun onBeforeLogin(loginCallable: suspend () -> Unit) {
        loginCallable()
    }

    abstract suspend fun onPinLogin(pin: String)
    abstract suspend fun onEmailLogin(email: String)
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
        emailFormBinding.build { email ->
            hideKeyboard(binding.root)
            launch {
                onBeforeLogin {
                    startLoading()
                    onEmailLogin(email)
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
                .observe(this) { style: EmailAndPinLoginViewModel.LoginStyle ->
                    when (style) {
                        EmailAndPinLoginViewModel.LoginStyle.PIN -> showPinLogin()
                        EmailAndPinLoginViewModel.LoginStyle.EMAIL -> showUserPassLogin()
                    }
                }
    }

    private fun showPinLogin() {
        changeFormContent(pinBinding.root, "Utilizar\nEmail")
    }

    private fun showUserPassLogin() {
        changeFormContent(emailFormBinding.root, "Utilizar\nPIN")
    }

    private fun changeFormContent(content: View, switchButtonText: String) {
        binding.apply {
            layoutFormContent.animateFadeOut(150L) {
                layoutFormContent.removeAllViews()
                layoutFormContent += content
                layoutFormContent.animateFadeIn(150L)
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
        return R.layout.activity_email_and_pin_login
    }
}