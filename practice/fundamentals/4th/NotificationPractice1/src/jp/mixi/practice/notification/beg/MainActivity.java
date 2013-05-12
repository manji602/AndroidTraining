
package jp.mixi.practice.notification.beg;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, SubActivity.class);
        // TODO ここで通知を表示する
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = builder
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Sample Notification")
                .setContentText("This is a sample notification.")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }
}