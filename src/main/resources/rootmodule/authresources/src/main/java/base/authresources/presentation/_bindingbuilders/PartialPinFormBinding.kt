package base.authresources.presentation._bindingbuilders

fun PartialPinFormBinding.build(pinLength: Int, onPinHandler: (pin: String) -> Unit) {
    editPin.digitLength = pinLength
    editPin.setOnTextChangeListener { text: String? ->
        if (text?.length == pinLength) {
            onPinHandler(text)
            editPin.reset()
        }
    }
}