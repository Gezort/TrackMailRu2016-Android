package com.vincent.tatiana.homeworktrack;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tatiana on 10/23/16.
 */

public enum AppTimer {
    INSTANCE;

    private IOnTimerListener mListener;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int mTimerTickNumber;
    private int mTicks;

    private void initTimerTask() {
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                ++mTicks;

                if (mListener != null) {
                    mListener.onTick(mTicks);
                }

                if (mTimerTickNumber == mTicks) {
                    if (mListener != null) {
                        mListener.onFinish();
                    }

                    stop();
                }
            }
        };
    }

    public void setListener(IOnTimerListener listener) {
        mListener = listener;
    }

    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }
    }

    public boolean isTicking() {
        return mTimer != null;
    }

    public int getTicks() {
        return mTicks;
    }

    public void start(long tickTime, int timerTickNumber) {
        stop();

        mTicks = 0;
        mTimerTickNumber = timerTickNumber;

        mTimer = new Timer();
        initTimerTask();
        mTimer.schedule(mTimerTask, 0, tickTime);
    }
}
