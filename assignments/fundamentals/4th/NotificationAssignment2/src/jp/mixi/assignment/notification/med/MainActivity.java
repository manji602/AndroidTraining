
package jp.mixi.assignment.notification.med;

import jp.mixi.assignment.notification.med.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class MainActivity extends Activity {
    public static final String ACTION_VIEW_MY_OWN = "jp.mixi.assignment.notification.med.android.intent.action.VIEW_MY_OWN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(ACTION_VIEW_MY_OWN);
        // TODO ここで通知を表示する
        // TODO intent には、ACTION_VIEW_MY_OWN の action をセットするだけにしておく
        // TODO 通知をタップした時に、複数の選択肢が表示されるが、どれがどの Activity に対応しているかがわかりづらくなっているので、AndroidManifest を編集して表示をわかりやすくする
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = builder
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Sample Notification")
                .setContentText("This is a sample notification.")
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Notification!!!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        notification.number = 5;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }
}