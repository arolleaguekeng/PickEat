package cm.pam.pickeat.model
import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class StoryModel(val authorId: Long, val menuId: Int, val storyId:Int,
                      var initTime: Date = Date(), val image: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        TODO("initTime"),
        parcel.readString()!!
    ){}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(authorId)
        parcel.writeInt(menuId)
        parcel.writeInt(storyId)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryModel> {
        override fun createFromParcel(parcel: Parcel): StoryModel {
            return StoryModel(parcel)
        }

        override fun newArray(size: Int): Array<StoryModel?> {
            return arrayOfNulls(size)
        }
    }
}