package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel(val phoneNumber: Long, val name: String?, val profile: String
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

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}