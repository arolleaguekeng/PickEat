package cm.pam.pickeat.Service.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.model.HomeCardMeal
import cm.pam.pickeat.model.photomeal


class photoMealAdapter(private  val homelist:ArrayList<photomeal>):
    RecyclerView.Adapter<photoMealAdapter.MyViewHolder>()
{
    class MyViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {

        var photo: ImageView = viewItem.findViewById(R.id.imagereycleview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): photoMealAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_home, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.photo.setImageResource(homelist[position].images)
    }

    override fun getItemCount(): Int {
        return homelist.size

    }



}