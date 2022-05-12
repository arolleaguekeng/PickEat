package cm.pam.pickeat.models

import android.os.Parcel
import android.os.Parcelable

data class Menu(
    val categoryId: Int,
    val categoryName: String,
    val description: String,
    val image: List<Image>,
    val likesNumber: Int,
    val menuId: Int,
    val menuName: String,
    val user: List<User>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Image)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        TODO("user")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(categoryId)
        parcel.writeString(categoryName)
        parcel.writeString(description)
        parcel.writeTypedList(image)
        parcel.writeInt(likesNumber)
        parcel.writeInt(menuId)
        parcel.writeString(menuName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Menu> {
        override fun createFromParcel(parcel: Parcel): Menu {
            return Menu(parcel)
        }

        override fun newArray(size: Int): Array<Menu?> {
            return arrayOfNulls(size)
        }
    }
}