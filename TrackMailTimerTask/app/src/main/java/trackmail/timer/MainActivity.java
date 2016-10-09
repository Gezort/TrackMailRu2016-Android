package trackmail.timer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLifeCycle();
    }

    private void startLifeCycle() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startTimerActivity();
    }

    private void startTimerActivity() {
        Intent intent = new Intent(this, TimerActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
