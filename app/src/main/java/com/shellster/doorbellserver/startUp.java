package com.shellster.doorbellserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class startUp extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            context.startService(new Intent(context, watchDog.class));
        }
    }
}