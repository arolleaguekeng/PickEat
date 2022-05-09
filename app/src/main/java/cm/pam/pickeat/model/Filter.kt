package cm.pam.pickeat.model

import android.os.Parcel
import android.os.Parcelable

class Filter(val typeOfMeal: TypeOfMeal?, val region: Region?, val eatingSystem: EatingSystem?,
             val nutrients: List<Nutrient>?, val flavors: List<Flavor>?, val complements: List<Complement>?):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(TypeOfMeal::class.java.classLoader),
        parcel.readParcelable(Region::class.java.classLoader),
        parcel.readParcelable(EatingSystem::class.java.classLoader),
        parcel.createTypedArrayList(Nutrient),
        parcel.createTypedArrayList(Flavor),
        parcel.createTypedArrayList(Complement)
    ) {
    }

    class TypeOfMeal(val id: Int, val designation: String):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(designation)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<TypeOfMeal> {
            override fun createFromParcel(parcel: Parcel): TypeOfMeal {
                return TypeOfMeal(parcel)
            }

            override fun newArray(size: Int): Array<TypeOfMeal?> {
                return arrayOfNulls(size)
            }
        }
    }

    class Region(val regionId: Int, val name: String, val description: String):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(regionId)
            parcel.writeString(name)
            parcel.writeString(description)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Region> {
            override fun createFromParcel(parcel: Parcel): Region {
                return Region(parcel)
            }

            override fun newArray(size: Int): Array<Region?> {
                return arrayOfNulls(size)
            }
        }

    }

    class Nutrient(val nutrientId: Int, val designation: String):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(nutrientId)
            parcel.writeString(designation)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Nutrient> {
            override fun createFromParcel(parcel: Parcel): Nutrient {
                return Nutrient(parcel)
            }

            override fun newArray(size: Int): Array<Nutrient?> {
                return arrayOfNulls(size)
            }
        }

    }

    class Flavor(val flavorId: Int, val designation: String):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(flavorId)
            parcel.writeString(designation)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Flavor> {
            override fun createFromParcel(parcel: Parcel): Flavor {
                return Flavor(parcel)
            }

            override fun newArray(size: Int): Array<Flavor?> {
                return arrayOfNulls(size)
            }
        }

    }

    class Complement(val complementId: Int, val name: String):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(complementId)
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Complement> {
            override fun createFromParcel(parcel: Parcel): Complement {
                return Complement(parcel)
            }

            override fun newArray(size: Int): Array<Complement?> {
                return arrayOfNulls(size)
            }
        }

    }

    class EatingSystem(val systemId: Int, val designation: String):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(systemId)
            parcel.writeString(designation)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<EatingSystem> {
            override fun createFromParcel(parcel: Parcel): EatingSystem {
                return EatingSystem(parcel)
            }

            override fun newArray(size: Int): Array<EatingSystem?> {
                return arrayOfNulls(size)
            }
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(typeOfMeal, flags)
        parcel.writeParcelable(region, flags)
        parcel.writeParcelable(eatingSystem, flags)
        parcel.writeTypedList(nutrients)
        parcel.writeTypedList(flavors)
        parcel.writeTypedList(complements)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Filter> {
        override fun createFromParcel(parcel: Parcel): Filter {
            return Filter(parcel)
        }

        override fun newArray(size: Int): Array<Filter?> {
            return arrayOfNulls(size)
        }
    }

}

