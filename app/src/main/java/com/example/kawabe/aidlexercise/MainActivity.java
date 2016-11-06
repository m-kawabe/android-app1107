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

    public void add(View view) {
        EditText text1 = (EditText)findViewById(R.id.editText);
        EditText text2 = (EditText)findViewById(R.id.editText2);

        int value1 = Integer.parseInt(text1.getText().toString());
        int value2 = Integer.parseInt(text2.getText().toString());

        int result = 0;
        try {
            result = mService.add(value1, value2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("" + result);
    }
}
