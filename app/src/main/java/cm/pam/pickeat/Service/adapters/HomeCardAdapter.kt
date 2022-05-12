package cm.pam.pickeat.Service.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.model.HomeCardMeal


class HomeCardAdapter(private  val homelist:ArrayList<HomeCardMeal>):
RecyclerView.Adapter<HomeCardAdapter.MyViewHolder>()
{
    class MyViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem) {
        var nameuser: TextView = viewItem.findViewById(R.id.name_user)
        var info: TextView = viewItem.findViewById(R.id.infoH)
        var description: TextView = viewItem.findViewById(R.id.descriptionH)
        var photo: ImageView = viewItem.findViewById(R.id.recyclerView2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_home, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.description.text = homelist[position].descriptions
        holder.nameuser.text=homelist[position].nameuser
        holder.info.text=homelist[position].info
        holder.photo.setImageResource(homelist[position].images)
    }

    override fun getItemCount(): Int {
        return homelist.size

    }

}

