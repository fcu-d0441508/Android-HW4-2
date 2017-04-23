package com.example.user.myapplication2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver {

    static int id = 70000;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String msg = intent.getStringExtra("KEY_MSG");

        Intent newintent = new Intent();
        newintent.setClass(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_NAME",msg);
        newintent.putExtras(bundle);
        PendingIntent  pendingIntent = PendingIntent.
                getActivity(context, 0, newintent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notify = newNotification(context, pendingIntent,
                "Hello",msg);

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(id++, notify);

    }

    @SuppressLint("NewApi")
    private Notification newNotification(Context context, PendingIntent pi, String title, String msg) {

        Notification.Builder  builder = new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pi);
        builder.setTicker(msg);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        Notification notify = builder.build();
        return notify;
    }
}
