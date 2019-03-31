package base.authresources.presentation._bindingbuilders

import arrow.core.Tuple2

fun PartialUserPasswordFormBinding.build(loginHandler: (user: String, pass: String) -> Unit) {
    layoutUser.backgroundColor = Color.TRANSPARENT
    layoutPassword.backgroundColor = Color.TRANSPARENT

    btnLogin.setOnClickListener {
        val (user, pass) = getUserAndPass()
        loginHandler(user, pass)
    }
}

private fun PartialUserPasswordFormBinding.getUserAndPass(): Tuple2<String, String> {
    val user = editUser.text?.toString().orEmpty()
    val pass = editPassword.text?.toString().orEmpty()
    return Tuple2(user, pass)
}