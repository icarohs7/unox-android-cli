package base.authresources.presentation

/**
 * Base activity with a view containing only a logo
 * and the facebook login button, handling the
 * authentication process
 */
abstract class EmailLoginActivity(
        private val appVersion: String
) : BaseBindingActivity<ActivityEmailLoginBinding>() {
    override fun onBindingCreated(savedInstanceState: Bundle?) {
        super.onBindingCreated(savedInstanceState)
        binding.txtVersion.text = appVersion
        toggleLoading(false)
        addOnLoadingListener(this::toggleLoading)
        setupBinding()
    }

    /**
     * Show and hide the loading state from
     * the StateView according to the parameter
     */
    private fun toggleLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> binding.stateView.displayLoadingState()
            false -> binding.stateView.hideStates()
        }
    }

    /**
     * Add listener to the facebook login
     * button
     */
    private fun setupBinding() {
        val email = binding.layoutEmailForm.editEmail.text?.toString().orEmpty()
        val doLogin = { launch { onEmailLogin(email) } }

        binding.layoutEmailForm.btnLogin.setOnClickListener { doLogin() }
    }

    abstract suspend fun onEmailLogin(email: String)

    override fun getLayout(): Int {
        return R.layout.activity_email_login
    }
}