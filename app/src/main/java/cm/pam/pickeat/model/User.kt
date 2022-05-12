package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class User(
    val balance: Int,
    val location: Location,
    val locationId: Int,
    val name: String,
    val phoneNumber: Int,
    val profile: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(Location::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(balance)
        parcel.writeParcelable(location, flags)
        parcel.writeInt(locationId)
        parcel.writeString(name)
        parcel.writeInt(phoneNumber)
        parcel.writeString(profile)
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