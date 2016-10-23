package com.vincent.tatiana.homeworktrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IOnTimerListener {

    private static final int TICK_NUMBER = 10;

    private Button mTimerButton;
    private TextView mTextView;
    private boolean mIsTicking;
    private OnTickRunnable mOnTickRunnable = new OnTickRunnable();
    private OnFinishRunnable mOnFinishRunnable = new OnFinishRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);

        mTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsTicking) {
                    mTimerButton.setText(R.string.timer_button_start);
                    AppTimer.INSTANCE.stop();
                } else {
                    AppTimer.INSTANCE.start(1000, TICK_NUMBER);
                    mTimerButton.setText(R.string.timer_button_stop);
                }

                mIsTicking = !mIsTicking;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppTimer.INSTANCE.setListener(this);

        int ticks = AppTimer.INSTANCE.getTicks();
        if (ticks != 0) {
            mTextView.setText(NumberGenerator.getNumber(ticks, getResources()));

            mIsTicking = AppTimer.INSTANCE.isTicking();
            mTimerButton.setText(
                    mIsTicking ? R.string.timer_button_stop : R.string.timer_button_start);
        }
    }

    @Override
    protected void onPause() {
        AppTimer.INSTANCE.setListener(null);
        super.onPause();
    }

    @Override
    public void onTick(int ticks) {
        mOnTickRunnable.setTicks(ticks);

        runOnUiThread(mOnTickRunnable);
    }

    @Override
    public void onFinish() {
        runOnUiThread(mOnFinishRunnable);
    }

    private class OnTickRunnable implements Runnable {
        private int mTicks;

        public void setTicks(int ticks) {
            mTicks = ticks;
        }

        @Override
        public void run() {
            mTextView.setText(NumberGenerator.getNumber(mTicks, getResources()));
        }
    }

    private class OnFinishRunnable implements Runnable {
        @Override
        public void run() {
            mTimerButton.setText(R.string.timer_button_start);
            mIsTicking = false;
        }
    }
}
