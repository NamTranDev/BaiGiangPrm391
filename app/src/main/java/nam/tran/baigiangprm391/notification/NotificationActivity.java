package nam.tran.baigiangprm391.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Random;

import nam.tran.baigiangprm391.R;

public class NotificationActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private NotificationChannel notificationChannel;
    private Notification.Builder builder;
    private final String channelId = "i.apps.notifications";
    private final String description = "Test notification";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        findViewById(R.id.bt_notification_not_allow_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(false);
            }
        });

        findViewById(R.id.bt_notification_allow_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(true);
            }
        });

        findViewById(R.id.bt_notification_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancelAll();
            }
        });
    }

    void sendNotification(boolean allowClick) {
        Intent intent = new Intent(NotificationActivity.this, ResultNotificationActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String title = "Đây là title notification";
        String content = allowClick ? "Đây là content notification cho phep click" : "Đây là content notification";

        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        builder = new Notification.Builder(NotificationActivity.this, channelId)
                // Các giá trị có thể là PRIORITY_DEFAULT, PRIORITY_HIGH, PRIORITY_LOW, hoặc PRIORITY_MAX.
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setAutoCancel(true);

        // Notification Actions
        // NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_action, "Action", pendingIntent);
        // builder.addAction(action);

        // Notification Lifecycle : Notifications có một vòng đời, từ lúc được tạo đến khi người dùng tương tác
        // với chúng hoặc khi chúng bị hủy bỏ

        if (allowClick)
            builder.setContentIntent(pendingIntent);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }
}
