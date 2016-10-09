package trackmail.timer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by miron on 06.10.16.
 */
public class TimerActivity extends AppCompatActivity {

    private static final int START = R.string.start_text;
    private static final int STOP = R.string.stop_text;
    private static final int MAX_TIME = 1000;

    private Button mButton;
    private TextView mTextView;
    private int mCurrentTime;
    private int mButtonTextId;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (mCurrentTime <= MAX_TIME) {
                mTextView.setText(String.format("%d", mCurrentTime));
                if (mCurrentTime == 0) {
                    mCurrentTime = 1;
                }
                ++mCurrentTime;
                timerHandler.postDelayed(this, 1000);
            } else {
                changeState();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mCurrentTime = 0;
        mButtonTextId = START;
        mTextView = (TextView) findViewById(R.id.timer_text);
        mButton = (Button) findViewById(R.id.timer_button);

        if (savedInstanceState != null && savedInstanceState.containsKey("text")) {
            mButtonTextId = savedInstanceState.getInt("text");
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("time")) {
            mCurrentTime = savedInstanceState.getInt("time");
        }
        mButton.setText(mButtonTextId);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonTextId == STOP) {
                    timerHandler.removeCallbacks(timerRunnable);
                    changeState();
                } else {
                    timerHandler.postDelayed(timerRunnable, 0);
                    changeState();
                }
            }
        });
        if (mCurrentTime != 0) {
            if (mButtonTextId == STOP) {
                timerHandler.postDelayed(timerRunnable, 0);
            } else {
                mTextView.setText(String.format("%d", mCurrentTime));
            }
        }
    }

    private void changeState() {
        if (mButtonTextId == START) {
            mButtonTextId = STOP;
            mCurrentTime = 1;
        } else {
            mButtonTextId = START;
        }
        mButton.setText(mButtonTextId);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCurrentTime != 0) {
            mCurrentTime = Integer.parseInt(mTextView.getText().toString());
        }
        outState.putInt("text", mButtonTextId);
        outState.putInt("time", mCurrentTime);
    }
}
