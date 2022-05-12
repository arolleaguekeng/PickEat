package cm.pam.pickeat.Service.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.models.User
import cm.pam.pickeat.repository.ItemClickListener

class FollowAdapter(var  items: MutableList<User>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<FollowersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowersViewHolder {
        return FollowersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false))
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        holder.bindData(items[position])

        holder.button.setOnClickListener(){
            itemClickListener.onButtonClicked(items[position])
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener(){
            itemClickListener.onItemClicked(items[position], it)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = items.size

    fun reload(list: MutableList<User>){
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

}