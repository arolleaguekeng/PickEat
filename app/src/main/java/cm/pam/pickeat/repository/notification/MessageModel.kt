package cm.pam.pickeat.controller.notification

import android.widget.ImageView
import com.bumptech.glide.Glide

class MessageModel constructor(
    var senderId: String = "",
    var receiverId: String = "",
    var message: String = "",
    var date: String = "",
    var type: String = ""

) {
    companion object {

        @JvmStatic
        public fun loadImage(imageView: ImageView, image: String?) {
            if (image != null) {
                Glide.with(imageView.context).load(image).into(imageView)
            }
        }
    }
}