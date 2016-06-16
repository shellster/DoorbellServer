package com.shellster.doorbellserver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class doorbellserver extends Activity {
    private static Context context;

    public static Context getAppContext() {
        return doorbellserver.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            doorbellserver.context = getApplicationContext();
            startService(new Intent(this, watchDog.class));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
