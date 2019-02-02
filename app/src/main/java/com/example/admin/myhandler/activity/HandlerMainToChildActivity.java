package com.example.admin.myhandler.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.myhandler.R;

public class HandlerMainToChildActivity extends Activity {

    private Handler handler;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_main_to_child);
        Log.e("TAG_0",Looper.getMainLooper().toString());
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = Message.obtain();
                message.obj = "MainToChild";
                handler.sendMessage(message);
            }
        });
        new Thread(new ChildRunnable()).start();

    }

    class ChildRunnable implements Runnable{

        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.e("TAG",msg.obj+"");
                }
            };
            Log.e("TAG_2",Looper.myLooper().toString());
            Looper.loop();

        }
    }
}
