package com.example.android.notepad;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;


public class RemindActionBroadcast extends BroadcastReceiver {
    public static int id=0;
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification.Builder mbuilder=new Notification.Builder(context);
        //ChannelId为"001",ChannelName为"my_channel"
        NotificationChannel channel = new NotificationChannel("1",
                "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        notificationManager.createNotificationChannel(channel);
        mbuilder.setChannelId("1");

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
        //NotificationManager notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        //Notification.Builder mbuilder=new Notification.Builder(context);
        mbuilder.setContentTitle(intent.getStringExtra("title"));
        mbuilder.setContentText(intent.getStringExtra("context"));
        mbuilder.setSmallIcon(R.mipmap.ic_launcher);
        mbuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher));
        mbuilder.setContentIntent(pendingIntent);
        mbuilder.setAutoCancel(true);
        Notification notification=mbuilder.build();
        notificationManager.notify(id++,notification);//能够传送多条消息

    }
}
