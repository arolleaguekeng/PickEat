package cm.pam.pickeat.Service.upload

import cm.pam.pickeat.model.MenuModel
import cm.pam.pickeat.model.UserModel


class UploadPublication {

    private lateinit var menu: MenuModel
    private lateinit var category: CharCategory
    private lateinit var description: String
    private lateinit var photo: String
    private var price: Float = 0f
    private lateinit var user: UserModel
    private var addon: ArrayList<String>
    private var commission: Float = 0f
    set(value){field = value}

    constructor(){
        addon = ArrayList()
    }
    constructor(menu: MenuModel, category: CharCategory, photo: String,
                description: String, price: Float,
                commission: Float, user: UserModel){
        this.menu = menu
        this.category = category
        this.description = description
        this.photo = photo
        this.price = price
        this.addon = ArrayList()
    }
    public fun addAddon(addon: String){
        this.addon.add(addon)
    }
}