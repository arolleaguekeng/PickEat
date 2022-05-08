package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class OrderModel(val orderId: Int, val authorId: Long, val receiverId: Long,
                      val publicationId: Int, val status: Boolean, var orderDate: Date, val quantity: Int):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        TODO("orderDate"),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(orderId)
        parcel.writeLong(authorId)
        parcel.writeLong(receiverId)
        parcel.writeInt(publicationId)
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderModel> {
        override fun createFromParcel(parcel: Parcel): OrderModel {
            return OrderModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderModel?> {
            return arrayOfNulls(size)
        }
    }
}