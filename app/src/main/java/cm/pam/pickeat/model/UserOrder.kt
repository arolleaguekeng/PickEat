package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class UserOrder(
    val received: List<Order>,
    val sent: List<Order>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Order)!!,
        parcel.createTypedArrayList(Order)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(received)
        parcel.writeTypedList(sent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserOrder> {
        override fun createFromParcel(parcel: Parcel): UserOrder {
            return UserOrder(parcel)
        }

        override fun newArray(size: Int): Array<UserOrder?> {
            return arrayOfNulls(size)
        }
    }
}