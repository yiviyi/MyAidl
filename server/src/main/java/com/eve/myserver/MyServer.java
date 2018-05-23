package com.eve.myserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyServer extends Service {

    private boolean isRun = true;
    @Override
    public void onCreate() {
        Log.e("MyServer", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (isRun) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 10000) {
                        i = 0;
                    }
                    Log.e("myserver", "run:" + i++);
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isRun = false;
        Log.e("MyServer", "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyServer", "onBind");
        return null;
    }
}
