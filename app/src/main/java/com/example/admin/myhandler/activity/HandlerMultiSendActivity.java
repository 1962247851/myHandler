package com.example.admin.myhandler.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.myhandler.R;

public class HandlerMultiSendActivity extends AppCompatActivity {

    private MainHandler mainHandler;
    private ChildHandler childHandler;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_multi_send);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Message message = Message.obtain();
                message.obj ="子线程发送给子线程消息";
                childHandler = new ChildHandler(Looper.myLooper());
                childHandler.sendMessage(message);
                Looper.loop();
            }
        }).start();
    }

    class MainHandler extends Handler{

        public MainHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.e("MainHandler",msg.obj+"");
            Message message = Message.obtain();
            message.obj = "主线程将消息再次传递给子线程";
            childHandler.sendMessage(message);
        }
    }

    class ChildHandler extends Handler{

        public ChildHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if(count == 0){
                Log.e("ChildHandler_1",msg.obj+"");
                mainHandler = new MainHandler(Looper.getMainLooper());
                Message message = Message.obtain();
                message.obj = "子线程发送给主线程消息";
                mainHandler.sendMessage(message);
                count++;
            }else{
                Log.e("ChildHandler_2",msg.obj+"");
            }
        }
    }
}
