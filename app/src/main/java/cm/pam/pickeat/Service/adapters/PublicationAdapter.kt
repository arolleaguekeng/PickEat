package cm.pam.pickeat.Service.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.models.Publication


class PublicationAdapter(private val publications : ArrayList<Publication>) : RecyclerView.Adapter<PublicationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publication_item, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*holder.category.text = publications[position].mealModel.categoryName
        holder.meal.text = publications[position].mealModel.name
        holder.price.text = publications[position].price.toString()
        holder.description.text = publications[position].description
        holder.photo.setImageResource(publications[position].image)*/
    }

    override fun getItemCount (): Int {
        return publications.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal lateinit var photo: ImageView
        internal  lateinit var category: TextView
        internal lateinit var meal: TextView
        internal lateinit var price: TextView
        internal lateinit var description: TextView

        init {
            photo = itemView.findViewById(R.id.publicationImage) as ImageView
            category = itemView.findViewById<View>(R.id.category) as TextView
            meal = itemView.findViewById<View>(R.id.meal) as TextView
            price = itemView.findViewById<View>(R.id.price) as TextView
            description = itemView.findViewById<View>(R.id.description) as TextView
        }
    }
}