package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class PublicationDetail(
    val authorId: Int,
    val availableUntil: String,
    val balance: Int,
    val city: String,
    val comment: List<Comment>,
    val createdAt: String,
    val description: String,
    val images: List<Image>,
    val locationId: Int,
    val menuId: Int,
    val name: String,
    val price: Int,
    val profile: Any,
    val publicationId: Int,
    val quater: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createTypedArrayList(Comment)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Image)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        TODO("profile"),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(authorId)
        parcel.writeString(availableUntil)
        parcel.writeInt(balance)
        parcel.writeString(city)
        parcel.writeTypedList(comment)
        parcel.writeString(createdAt)
        parcel.writeString(description)
        parcel.writeTypedList(images)
        parcel.writeInt(locationId)
        parcel.writeInt(menuId)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(publicationId)
        parcel.writeString(quater)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PublicationDetail> {
        override fun createFromParcel(parcel: Parcel): PublicationDetail {
            return PublicationDetail(parcel)
        }

        override fun newArray(size: Int): Array<PublicationDetail?> {
            return arrayOfNulls(size)
        }
    }
}