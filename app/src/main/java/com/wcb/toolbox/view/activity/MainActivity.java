package com.wcb.toolbox.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.wcb.clock.view.activity.ClockMainActivity;
import com.wcb.toolbox.R;

public class MainActivity extends BaseActivity {

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
