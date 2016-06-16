package com.shellster.doorbellserver;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by shellster on 6/15/2016.
 */
public class udpServer  extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String[] params) {
        udpServerRun();
        return "Done";
    }

    private void udpServerRun() {
        String lText;
        byte[] lMsg = new byte[2048];
        MediaPlayer mediaPlayer=MediaPlayer.create(doorbellserver.getAppContext(),R.raw.doorbell);

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
