package cm.pam.pickeat.repository

import android.view.View
import cm.pam.pickeat.model.User

interface ItemClickListener {

    fun onButtonClicked(user: User)

    fun onItemClicked(user: User, view: View)
}