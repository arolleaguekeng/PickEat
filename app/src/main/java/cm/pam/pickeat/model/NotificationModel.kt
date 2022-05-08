package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable

data class NotificationModel(val authorId: Long, val receiverId: Long,
val title: String,val notificationId: Int, val message: String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(authorId)
        parcel.writeLong(receiverId)
        parcel.writeString(title)
        parcel.writeInt(notificationId)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotificationModel> {
        override fun createFromParcel(parcel: Parcel): NotificationModel {
            return NotificationModel(parcel)
        }

        override fun newArray(size: Int): Array<NotificationModel?> {
            return arrayOfNulls(size)
        }
    }
}