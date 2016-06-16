package com.shellster.doorbellserver;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class watchDog extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        try {
            super.onCreate();
            runAsForeground();
            udpServer job = new udpServer();
            job.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Doorbell Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    public void onDestroy() {
        Intent intent = new Intent("com.android.doorbellserver");
        sendBroadcast(intent);
    }

    private void runAsForeground(){
        Notification notification=new NotificationCompat.Builder(this)
                .setContentText("Doorbell").build();

        startForeground(1, notification);
    }
}
