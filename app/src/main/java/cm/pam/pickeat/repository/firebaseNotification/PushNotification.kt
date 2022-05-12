package cm.pam.pickeat.controller.firebaseNotification

import cm.pam.pickeat.controller.firebaseNotification.NotificationData

data class PushNotification(
    val data: NotificationData,
    val to: String
)