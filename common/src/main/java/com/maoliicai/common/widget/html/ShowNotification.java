package com.maoliicai.common.widget.html;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


import com.maoliicai.common.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * yangyoupeng  on 2018/5/9.
 * <p>
 * 通知下载文件
 */

public class ShowNotification {

    private static NotificationManager mNotificationManager;
    private static Notification mNotification;
    private static RemoteViews rv;
    private static final int NOTIFICATION_ID = 9999;

    public static void showNotification(Context context,long fileSize, long downLoadFileSize) {
        mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotification = new Notification();
        mNotification.icon = R.mipmap.ic_logo_application;
        mNotification.tickerText = "开始下载";

        rv = new RemoteViews(context.getPackageName(), R.layout.notification_download_update);
        rv.setImageViewResource(R.id.iv_logo, R.mipmap.ic_logo_application);
        rv.setTextViewText(R.id.txt_notice, "正在下载文件，已完成" + (downLoadFileSize * 100 / fileSize) + "%");
        int progress = (int) (downLoadFileSize * 100 / fileSize);
        rv.setProgressBar(R.id.progBar, 100, progress, false);
        mNotification.contentView = rv;

        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        mNotification.contentIntent = pendingIntent;
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
    }


    public  static  void canle(){
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
