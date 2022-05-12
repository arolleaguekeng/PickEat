package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Following(
    val followedId: Int,
    val followerId: Int,
    val startAt: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(followedId)
        parcel.writeInt(followerId)
        parcel.writeString(startAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Following> {
        override fun createFromParcel(parcel: Parcel): Following {
            return Following(parcel)
        }

        override fun newArray(size: Int): Array<Following?> {
            return arrayOfNulls(size)
        }
    }
}