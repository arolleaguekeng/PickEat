package cm.pam.pickeat.Service.upload

import cm.pam.pickeat.Service.upload.UploadAddress
import java.util.*
import kotlin.collections.ArrayList

class UploadUser {
    var phone: String = String()
    var name: String = String()
    var photo: String = String()
    var address: UploadAddress = UploadAddress()
    var balanced: Double = 0.0
    var follower: ArrayList<Objects>? = ArrayList()
    var follows: ArrayList<Objects>? = ArrayList()

    constructor(){
        follower = ArrayList()
        follows = ArrayList()
    }

    constructor(phoneNumber: String):this(){
       phone = phoneNumber
    }

    constructor(phoneNumber: String, name: String,
                photo: String, address: UploadAddress,
                follower: ArrayList<Objects>?,
                follows: ArrayList<Objects>?):this(phoneNumber){
        this.name = name
        this.photo = photo
        this.follower = follower
        this.follows = follows
        this.address = address
    }
}