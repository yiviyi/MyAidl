package com.eve.myserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart, btnBind, btnStop, btnUnbind;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btnStart = findViewById(R.id.btnStart);
        btnBind = findViewById(R.id.btnBind);
        btnUnbind = findViewById(R.id.btnUnbind);
        btnStop = findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
//                Intent intent = new Intent();
//                intent.setAction("com.eve.myserver");
//                intent.setPackage("com.eve.myserver.MyServer");
                startService(new Intent(this,MyServer.class));
                break;
            case R.id.btnBind:
                break;
            case R.id.btnStop:
//                Intent intent2 = new Intent();
//                intent2.setAction("com.eve.myserver");
//                intent2.setPackage("com.eve.myserver.MyServer");
                stopService(new Intent(this,MyServer.class));
                break;
            case R.id.btnUnbind:
                break;
            default:
                break;
        }
    }
}
