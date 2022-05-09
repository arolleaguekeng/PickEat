package cm.pam.pickeat.repository

import cm.pam.pickeat.model.Address
import java.util.*

class RestUser (
    val phoneNumber: Long, val registeredDate: Date, val name: String
    , val profile: ByteArray?, val address: Address, val photo: Int) {
}