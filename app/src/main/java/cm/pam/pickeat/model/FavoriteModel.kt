package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class FavoriteModel(val userId: Long, val publicationId: Int, val createdAt: Date):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        TODO("createdAt")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeInt(publicationId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteModel> {
        override fun createFromParcel(parcel: Parcel): FavoriteModel {
            return FavoriteModel(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteModel?> {
            return arrayOfNulls(size)
        }
    }
}