package cm.pam.pickeat.repository
import android.os.Parcel
import android.os.Parcelable



data class MenuRepository(val menuId: Int, val menuName: String,
                     val description: String, val categoriId: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(menuId)
        parcel.writeString(menuName)
        parcel.writeString(description)
        parcel.writeInt(categoriId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuRepository> {
        override fun createFromParcel(parcel: Parcel): MenuRepository {
            return MenuRepository(parcel)
        }

        override fun newArray(size: Int): Array<MenuRepository?> {
            return arrayOfNulls(size)
        }
    }
}