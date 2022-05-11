package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class User(val phoneNumber: Long, val name: String?, val profile: String
                     , val balance: Double, val locationId: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(phoneNumber)
        parcel.writeString(name)
        parcel.writeString(profile)
        parcel.writeDouble(balance)
        parcel.writeInt(locationId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}