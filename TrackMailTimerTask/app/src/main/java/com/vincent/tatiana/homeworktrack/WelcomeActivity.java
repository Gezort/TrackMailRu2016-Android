package com.vincent.tatiana.homeworktrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity implements IOnTimerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (savedInstanceState == null) {
            OneShotSleepTimer.INSTANCE.start(2000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        OneShotSleepTimer.INSTANCE.setListener(this);

        // If timer finished when we were not subscribed
        if (OneShotSleepTimer.INSTANCE.isFinished()) {
            startMainActivity();
        }
    }

    @Override
    protected void onPause() {
        OneShotSleepTimer.INSTANCE.setListener(null);
        super.onPause();
    }

    @Override
    public void onTick(int ticks) {
        //do nothing
    }

    @Override
    public void onFinish() {
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
