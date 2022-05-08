package cm.pam.pickeat.model

import java.util.*

data class UserModel(val phone: String, val registeredDate: Date, val name: String
                     , val profile: ByteArray, val address: AddressModel, val photo: Int) {
}