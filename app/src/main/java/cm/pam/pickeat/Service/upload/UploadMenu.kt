package cm.pam.pickeat.Service.upload

import cm.pam.pickeat.Service.upload.UploadCategory

class UploadMenu {
    var name: String = String()
    var description: String = String()
    var photo: String = String()
    var category: UploadCategory = UploadCategory()
    var note: Double = 0.0

    constructor()
    constructor(name: String, description: String, photo: String
                , category: UploadCategory, note: Double){
        this.name = name
        this.description = description
        this.photo = photo
        this.category = category
        this.note = note
    }
}