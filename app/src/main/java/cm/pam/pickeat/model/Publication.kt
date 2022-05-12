package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Publication(
    val nb_like: Int,
    val publicationId: Int,
    val publication_details: List<PublicationDetail>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        TODO("publication_details")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(nb_like)
        parcel.writeInt(publicationId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Publication> {
        override fun createFromParcel(parcel: Parcel): Publication {
            return Publication(parcel)
        }

        override fun newArray(size: Int): Array<Publication?> {
            return arrayOfNulls(size)
        }
    }
}