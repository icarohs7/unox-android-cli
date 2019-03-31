package base.corelibrary.domain.extensions

fun UnoxAndroidArch.AnimationType.toNavOptions(): NavOptions {
    return navOptions {
        anim {
            enter = enterRes
            exit = exitRes
            popEnter = enterRes
            popExit = exitRes
        }
    }
}
