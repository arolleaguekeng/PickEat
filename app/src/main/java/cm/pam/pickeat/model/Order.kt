package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Order(
    val authorId: Int,
    val orderDate: String,
    val orderId: Int,
    val publicationId: Int,
    val quantity: Int,
    val receiverId: Int,
    val status: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(authorId)
        parcel.writeString(orderDate)
        parcel.writeInt(orderId)
        parcel.writeInt(publicationId)
        parcel.writeInt(quantity)
        parcel.writeInt(receiverId)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}