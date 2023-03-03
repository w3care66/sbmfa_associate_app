package com.associate.sbmfa.FCM;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.associate.sbmfa.Activity.login.PinActivity;
import com.associate.sbmfa.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager mNotificationManager;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("message_fcm1","message_fcm1"+remoteMessage.getData());
        // Log.e("msg", "onMessageReceived: " +remoteMessage.getData()+" "+remoteMessage.getMessageType()+" "+remoteMessage.getNotification().getBody()+ " "+remoteMessage.getNotification().getTitle()+" "+remoteMessage.getTo()+" "+remoteMessage.getFrom()+" ");
         // Log.e("msg", "onMessageReceived: asdasd "+remoteMessage.getData().get("");
        //{body=Test message, type=user, sound=default, title=Friend Request}
        if(remoteMessage.getData()!=null){
            handleNotification(remoteMessage.getData().get("body"),remoteMessage.getData().get("title"),remoteMessage.getData().get("type"),remoteMessage.getData().get("challenge_id"));
        }

    }
    @Override
    public void onNewToken(String s) {
        sendRegistrationToServer(s);
    }
    private void handleNotification(String message, String title, String type, String challenge_id) {
        Intent intent = new Intent(this, PinActivity.class);
        intent.putExtra("from", type);
        intent.putExtra("challenge_id", challenge_id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);
        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0 , notificationBuilder.build());
    }
    private void sendRegistrationToServer(String token) {
// TODO: Implement this method to send token to your app server.
    }
}