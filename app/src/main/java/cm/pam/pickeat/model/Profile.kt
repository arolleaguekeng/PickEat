package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Profile(
    val follower: List<Follower>,
    val following: List<Following>,
    val phoneNumber: Int,
    val publication: Any,
    val user: User
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Follower)!!,
        parcel.createTypedArrayList(Following)!!,
        parcel.readInt(),
        TODO("publication"),
        TODO("user")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(follower)
        parcel.writeTypedList(following)
        parcel.writeInt(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Profile> {
        override fun createFromParcel(parcel: Parcel): Profile {
            return Profile(parcel)
        }

        override fun newArray(size: Int): Array<Profile?> {
            return arrayOfNulls(size)
        }
    }
}