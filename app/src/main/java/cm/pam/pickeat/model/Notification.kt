package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Notification(
    val authorId: Int,
    val message: String,
    val notificationId: Int,
    val receiverid: Int,
    val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(authorId)
        parcel.writeString(message)
        parcel.writeInt(notificationId)
        parcel.writeInt(receiverid)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notification> {
        override fun createFromParcel(parcel: Parcel): Notification {
            return Notification(parcel)
        }

        override fun newArray(size: Int): Array<Notification?> {
            return arrayOfNulls(size)
        }
    }
}