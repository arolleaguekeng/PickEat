package cm.pam.pickeat.repository.SendNotificationPack

class NotificationSender(val data: Data?, val to:String){
    constructor():this(null,""){}
}