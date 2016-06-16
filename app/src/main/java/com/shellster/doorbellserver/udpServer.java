package com.shellster.doorbellserver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class udpServer extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String[] params) {
        PackageManager pm = watchDog.getAppContext().getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.rcreations.ipcamviewerBasic");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        watchDog.getAppContext().startActivity(intent);

        udpServerRun();
        return "Done";
    }

    private void udpServerRun() {
        String lText;
        byte[] lMsg = new byte[2048];
        MediaPlayer mediaPlayer=MediaPlayer.create(watchDog.getAppContext(),R.raw.doorbell);

        while(true) {
            DatagramPacket dp = new DatagramPacket(lMsg, lMsg.length);
            DatagramSocket ds = null;

            try {
                ds = new DatagramSocket(20666);
                ds.setBroadcast(true);
                ds.receive(dp);
                lText = new String(lMsg, 0, dp.getLength());
                if(lText.trim().equals("doorbell"))
                    mediaPlayer.start();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (ds != null) {
                    ds.close();
                }
            }
        }
    }
}
