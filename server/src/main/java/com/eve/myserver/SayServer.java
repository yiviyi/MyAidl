package com.eve.myserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SayServer extends Service {
    public final String TAG = this.getClass().getSimpleName();
    private boolean isRun = true;
    private List<Guy> mGuys = new ArrayList<>();

    @Override
    public void onCreate() {
        Log.e("SayServer", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SayServer", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isRun = false;
        Log.e("SayServer", "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("SayServer", "onBind");
        return new ISayInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void sayHell0(String str) throws RemoteException {
                Log.e("SayServer", "say:" + str);
            }

            @Override
            public int sum(int a, int b) throws RemoteException {
                int result = a + b;
                Log.e("SayServer", a + "+" + b + "=" + result);
                return result;
            }

            @Override
            public Guy addGuy(Guy guy) throws RemoteException {
                synchronized (this) {
                    if (mGuys == null) {
                        mGuys = new ArrayList<>();
                    }
                    if (guy == null) {
                        Log.e(TAG, "addGuy but guy is null");
                        guy = new Guy();
                    }
                    guy.setAge(999);
                    if (!mGuys.contains(guy)) {
                        mGuys.add(guy);
                    }
                    Log.e(TAG, "add success" + mGuys.toString());
                }
                return null;
            }

            @Override
            public List<Guy> getGuys() throws RemoteException {
                synchronized (this) {
                    Log.e(TAG, "invoking getGuys");
                    if (mGuys != null) {
                        return mGuys;
                    } else {
                        return new ArrayList<>();
                    }
                }
            }
        };
    }
}
