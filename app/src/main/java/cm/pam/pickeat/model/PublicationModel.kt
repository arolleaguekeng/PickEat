package cm.pam.pickeat.model

import java.text.DateFormat

data class PublicationModel(var mealModel: MealModel, var description: String, var image: Int, val rate: Double = 0.0, val price: Double, val publicationHours: DateFormat = DateFormat.getDateInstance()){
}