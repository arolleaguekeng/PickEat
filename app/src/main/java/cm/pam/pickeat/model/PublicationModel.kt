package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class PublicationModel(val publicationId: Int, val menuId: Int, val authorId: Long, val description: String, val comments: List<Comment>?,
val createdAt: Date = Date(), val image: String, val availableUntil: Date, val price: Double, val like: List<Like>): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.createTypedArrayList(Comment),
        TODO("createdAt"),
        parcel.readString()!!,
        TODO("availableUntil"),
        parcel.readDouble(),
        parcel.createTypedArrayList(Like)!!
    ) {
    }

    class Like(val publicationId: Int, val userId: Long, val likedAt: Date):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readLong(),
            TODO("likedAt")
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(publicationId)
            parcel.writeLong(userId)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Like> {
            override fun createFromParcel(parcel: Parcel): Like {
                return Like(parcel)
            }

            override fun newArray(size: Int): Array<Like?> {
                return arrayOfNulls(size)
            }
        }

    }

    class Comment(val commentId: Int, val authorId: Long, val publicationId: Int, val createdAt: Date, val content: String): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readLong(),
            parcel.readInt(),
            TODO("createdAt"),
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(commentId)
            parcel.writeLong(authorId)
            parcel.writeInt(publicationId)
            parcel.writeString(content)
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(publicationId)
        parcel.writeInt(menuId)
        parcel.writeLong(authorId)
        parcel.writeString(description)
        parcel.writeTypedList(comments)
        parcel.writeString(image)
        parcel.writeDouble(price)
        parcel.writeTypedList(like)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PublicationModel> {
        override fun createFromParcel(parcel: Parcel): PublicationModel {
            return PublicationModel(parcel)
        }

        override fun newArray(size: Int): Array<PublicationModel?> {
            return arrayOfNulls(size)
        }
    }
}