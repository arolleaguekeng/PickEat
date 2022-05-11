import cm.pam.pickeat.model.Meal
import java.text.DateFormat

class Publication(var mealModel: Meal, var description: String, var image: Int, val rate: Double = 0.0, val price: Double, val publicationHours: DateFormat = DateFormat.getDateInstance()){
}