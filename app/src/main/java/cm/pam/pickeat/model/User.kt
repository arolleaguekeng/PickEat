package cm.pam.pickeat.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import cm.pam.pickeat.currentUser
import java.util.*

data class User(val phoneNumber: Long, val name: String?, val profile: Int
                , val balance: Double, val locationId: Int): Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt()!!,
        parcel.readDouble(),
        parcel.readInt()


    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(phoneNumber)
        parcel.writeString(name)
        parcel.writeInt(profile)
        parcel.writeDouble(balance)
        parcel.writeInt(locationId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}