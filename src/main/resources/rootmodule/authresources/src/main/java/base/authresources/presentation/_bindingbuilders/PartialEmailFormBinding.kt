package base.authresources.presentation._bindingbuilders

fun PartialEmailFormBinding.build(loginHandler: (email: String) -> Unit) {
    layoutEmail.backgroundColor = Color.TRANSPARENT
    btnLogin.setOnClickListener { loginHandler(getEmail()) }
}

private fun PartialEmailFormBinding.getEmail(): String {
    return editEmail.text?.toString().orEmpty()
}