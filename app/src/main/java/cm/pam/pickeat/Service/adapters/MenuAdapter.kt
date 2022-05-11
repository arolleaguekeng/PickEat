package cm.pam.pickeat.Service.adapters

import Publication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import cm.pam.pickeat.R
import cm.pam.pickeat.model.Menu


class MenuAdapter(private val menuList: ArrayList<Menu>):
    RecyclerView.Adapter<MenuAdapter.MyViewHolder>()  {

    class MyViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
        internal var title: TextView = viewItem.findViewById(R.id.menuName)
        var description: TextView = viewItem.findViewById(R.id.menuDescription)
        var category: TextView = viewItem.findViewById(R.id.menuCategory)
        var region: TextView = viewItem.findViewById(R.id.menuRegion)
        var note: RatingBar = viewItem.findViewById(R.id.menuRatingBar)
        var photo: ImageView = viewItem.findViewById(R.id.menuPhoto)

        var users = arrayListOf<ImageView>(
            viewItem.findViewById(R.id.user1),
            viewItem.findViewById(R.id.user2),
            viewItem.findViewById(R.id.user3),
            viewItem.findViewById(R.id.user4),
            viewItem.findViewById(R.id.user5)
        )

        var reminder: TextView = viewItem.findViewById(R.id.reminderAuthor)

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_card, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*holder.title.text = menuList[position].title
        holder.description.text = menuList[position].description
        holder.category.text = menuList[position].category
        holder.region.text = menuList[position].region
        holder.note.rating = rateAverage(menuList[position].publicationModels).toFloat()
        holder.photo.setImageResource(menuList[position].photo)

        if(!menuList[position].publicationModels.isNullOrEmpty()){
            if(menuList[position].publicationModels!!.size!! > holder.users.size){
                var i = 0
                while (i!=holder.users.size){
                    holder.users[i].setImageResource(menuList[position].publicationModels!![i].image)
                    i++
                }
                holder.reminder.text = "+${menuList[position].publicationModels!!.size-i}"
            }
            else{
                var i = 0
                while (i<menuList[position].publicationModels!!.size){
                    holder.users[i].setImageResource(menuList[position].publicationModels!![i].image)
                    i++
                }
                holder.reminder.isVisible = false
            }
        }*/
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
    private fun rateAverage(publicationModels: ArrayList<Publication>?): Double{
        println("------------->Hello!")
        var average: Double = 0.0
       if(!publicationModels.isNullOrEmpty()){
           publicationModels!!.forEach{
               average += it.rate
           }
           return average/publicationModels.size
       }
        return 0.0
    }
}