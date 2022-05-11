package cm.pam.pickeat.controller.notification


import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

data class UserModel(
    val uid: String = ""
) {

    companion object {

        @JvmStatic
        fun loadImage(view: CircleImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view.context).load(imageUrl).into(view)
            }
        }
    }

}

