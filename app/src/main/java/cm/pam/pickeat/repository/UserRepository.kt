package cm.pam.pickeat.repository

import cm.pam.pickeat.model.AddressModel
import java.util.*

class UserRepository (
    val phoneNumber: Long, val registeredDate: Date, val name: String
    , val profile: ByteArray?, val address: AddressModel, val photo: Int) {
}