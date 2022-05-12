package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Comment(
    val authorId: Int,
    val commentId: Int,
    val content: String,
    val createAt: String,
    val publicationId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(authorId)
        parcel.writeInt(commentId)
        parcel.writeString(content)
        parcel.writeString(createAt)
        parcel.writeInt(publicationId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }
}