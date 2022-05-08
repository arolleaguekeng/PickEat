package cm.pam.pickeat.Service.upload

import android.media.audiofx.AudioEffect

class UploadCategory {
    var name: String = String()
    var description: String = String()
    var photo: String = String()

    constructor()
    constructor(name: String, description: String, photo: String){
        this.name = name
        this.description = description
        this.photo = photo
    }
}