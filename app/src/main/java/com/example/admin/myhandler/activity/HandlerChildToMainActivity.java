package com.example.admin.myhandler.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.admin.myhandler.R;

public class HandlerChildToMainActivity extends Activity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String data = (String) msg.obj;
            textView.setText("从子线程发送过来的消息"+data);
            childRunnable = null;
        }
    };

    private TextView textView;
    private ChildRunnable childRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_child_to_main);
        textView = (TextView) findViewById(R.id.tv);
        childRunnable = new ChildRunnable();
        childRunnable.run();
    }

    class ChildRunnable implements Runnable {

        @Override
        public void run() {
            Message message = Message.obtain();
            message.obj = "ChildToMain";
            handler.sendMessage(message);
        }
    }
}
