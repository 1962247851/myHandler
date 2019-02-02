package com.example.admin.myhandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.myhandler.activity.HandlerChildToMainActivity;
import com.example.admin.myhandler.activity.HandlerMainToChildActivity;
import com.example.admin.myhandler.activity.HandlerMultiSendActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button button[] = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        for (int i = 0; i < button.length; i++) {
            String strId = "but_" + i;
            int resId = getResources().getIdentifier(strId, "id", getPackageName());
            button[i] = (Button) findViewById(resId);
            button[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_0:
                startActivity(new Intent(this, HandlerMainToChildActivity.class));
                break;
            case R.id.but_1:
                startActivity(new Intent(this, HandlerChildToMainActivity.class));
                break;
            case R.id.but_2:
                startActivity(new Intent(this, HandlerMultiSendActivity.class));
                break;
        }
    }
}
