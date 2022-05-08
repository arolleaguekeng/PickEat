package cm.pam.pickeat.Service.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.model.MealModel



class MealAdapter(private val mealModels : ArrayList<MealModel>) : RecyclerView.Adapter<MealAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publication_item, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*holder.category.text = mealModels[position].categoryName
        holder.name.text = mealModels[position].name
        holder.description.text = mealModels[position].description
        holder.photo.setImageResource(mealModels[position].Image)*/
    }

    override fun getItemCount (): Int {
        return mealModels.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*internal var category: TextView = itemView.findViewById<View>(R.id.category) as TextView
        internal var name: TextView = itemView.findViewById(R.id.meal) as TextView
        internal var description: TextView = itemView.findViewById(R.id.description) as TextView
        internal var photo: ImageView = itemView.findViewById(R.id.menuPhoto)*/
    }
}