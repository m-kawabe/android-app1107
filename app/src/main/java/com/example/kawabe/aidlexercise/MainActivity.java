package com.example.kawabe.aidlexercise;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    IAdditionService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentservice = new Intent(this, AdditionService.class);
        bindService(intentservice, mConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IAdditionService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void showLocale(View view) {
        String result = "";
        try {
            result = mService.getLocale();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("" + result);
    }
}
