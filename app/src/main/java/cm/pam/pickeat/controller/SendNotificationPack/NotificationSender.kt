package cm.pam.pickeat.controller.SendNotificationPack

import cm.pam.pickeat.controller.SendNotificationPack.Data

class NotificationSender(val data: Data?, val to:String){
    constructor():this(null,""){}
}