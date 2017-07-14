package com.wcb.toolbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.wcb.clock.ClockMainActivity;
import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testModule(View v) {
        Intent intent = new Intent(this, ClockMainActivity.class);
        startActivity(intent);

    }
}
