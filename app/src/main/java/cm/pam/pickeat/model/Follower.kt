package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Follower(val followerId: Long, val followedId: Long, val startAt: Date):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        TODO("startAt")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(followerId)
        parcel.writeLong(followedId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Follower> {
        override fun createFromParcel(parcel: Parcel): Follower {
            return Follower(parcel)
        }

        override fun newArray(size: Int): Array<Follower?> {
            return arrayOfNulls(size)
        }
    }
}