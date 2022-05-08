package cm.pam.pickeat.model

data class MenuModel (var title: String, var category: String, var region: String, var description: String, var photo: Int, var publicationModels: ArrayList<PublicationModel>? = null) {
}