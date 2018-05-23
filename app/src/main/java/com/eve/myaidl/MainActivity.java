package com.eve.myaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eve.myserver.Guy;
import com.eve.myserver.ISayInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart, btnBind, btnStop, btnUnbind, btnSum, btnSay;
    private TextView tv;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btnStart = findViewById(R.id.btnStart);
        btnBind = findViewById(R.id.btnBind);
        btnUnbind = findViewById(R.id.btnUnbind);
        btnStop = findViewById(R.id.btnStop);
        btnSay = findViewById(R.id.btnSay);
        btnSum = findViewById(R.id.btnSum);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
        btnSay.setOnClickListener(this);
        btnSum.setOnClickListener(this);
    }

    private ISayInterface iSayInterface;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iSayInterface = ISayInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                Intent intent = new Intent();
                intent.setAction("com.eve.myserver.SayServer");
                intent.setPackage("com.eve.myserver");
//                intent.setComponent(new ComponentName("com.eve.mythread", "com.eve.mythread.MyService"));
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btnBind:
                break;
            case R.id.btnStop:
                break;
            case R.id.btnUnbind:
                int age = Integer.parseInt(et1.getText().toString().trim());
                String name = et2.getText().toString().trim();
                Guy guy = new Guy(age,name);
                try {
                    iSayInterface.addGuy(guy);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnSum:
                int a = Integer.parseInt(et1.getText().toString().trim());
                int b = Integer.parseInt(et2.getText().toString().trim());
                int result = 0;
                try {
                    result = iSayInterface.sum(a, b);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                tv.setText(a + "+" + b + "=" + result);
                break;
            case R.id.btnSay:
                try {
//                    iSayInterface.getGuys();
                    StringBuffer sb = new StringBuffer();
                    List<Guy> guys = new ArrayList<>();
                    guys.addAll(iSayInterface.getGuys());

                    for (Guy item:guys) {
                        sb.append("name:").append(item.getName()).append("   ,  age:").append(item.getAge());
                    }
                    tv.setText("welcome: "+sb.toString());
//                    iSayInterface.sayHell0(tv.getText().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
