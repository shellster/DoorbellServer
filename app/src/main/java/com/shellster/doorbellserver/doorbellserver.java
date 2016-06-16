package com.shellster.doorbellserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class doorbellserver extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            startService(new Intent(this, watchDog.class));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
