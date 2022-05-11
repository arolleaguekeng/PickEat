package cm.pam.pickeat.Service.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.model.Category





class CategoryAdapter(private val categoryModels : ArrayList<Category>, private val onCategoryClicked:(position: Int)->Unit) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_items, parent, false)
        return MyViewHolder (view, onCategoryClicked)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = categoryModels[position].title
        holder.background.setImageResource(categoryModels[position].background)
    }

    override fun getItemCount (): Int {
        return categoryModels.size
    }

    inner class MyViewHolder(itemView: View, private val onCategoryClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var name: TextView
        lateinit  var background: ImageView

        init{
            name = itemView.findViewById<TextView>(R.id.content)
            background = itemView.findViewById<ImageView>(R.id.background_category)
            println("Hello!")
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            onCategoryClicked(position)
        }
    }
}