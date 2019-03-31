package base.corelibrary.presentation.main

import arrow.core.Try

fun BaseMainActivity.toggleLoading(isLoading: Boolean) {
    binding.progressBar.isGone = !isLoading
}

fun BaseMainActivity.addBadgeToBottomNavIcon(iconIndex: Int, badgeView: View) {
    Try {
        val menuView = binding.bottomNav.getChildAt(0) as BottomNavigationMenuView
        val itemView = menuView.getChildAt(iconIndex) as BottomNavigationItemView
        itemView.addView(badgeView)
    }
}

fun BaseMainActivity.showNavHeaderNameWhen(condition: Boolean, name: String) {
    defaultNavHeaderLayoutUserName?.isVisible = condition
    defaultNavHeaderTxtName?.text = name
}

val BaseMainActivity.defaultNavHeaderLayoutUserName: LinearLayout?
    get() {
        return Try {
            val header = requireNotNull(drawer?.header)
            header.find<LinearLayout>(R.id.layout_user_name)
        }.orNull()
    }

val BaseMainActivity.defaultNavHeaderTxtName: TextView?
    get() {
        return Try {
            val header = requireNotNull(drawer?.header)
            header.find<TextView>(R.id.txt_name)
        }.orNull()
    }
