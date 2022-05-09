package cm.pam.pickeat.ui.model

import Publication

data class Menu (var title: String, var category: String, var region: String, var description: String, var photo: Int, var publicationModels: ArrayList<Publication>? = null) {
}