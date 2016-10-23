package com.vincent.tatiana.homeworktrack;

import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by tatiana on 10/23/16.
 */

public enum OneShotSleepTimer {
    INSTANCE;

    private IOnTimerListener mListener;
    private boolean mIsFinished;
    private int mSessionId;

    public synchronized void setListener(IOnTimerListener listener) {
        mListener = listener;
    }

    public synchronized void reset() {
        mIsFinished = false;
        ++mSessionId;
    }

    public synchronized boolean isFinished() {
        return mIsFinished;
    }

    public void start(final long sleepTime) {
        reset();

        new Task(sleepTime, mSessionId).execute();
    }

    private class Task extends AsyncTask<Void, Void, Void> {
        private final long mSleepTime;
        private final int mTaskSessionId;

        public Task(long sleepTime, int sessionId) {
            mSleepTime = sleepTime;
            this.mTaskSessionId = sessionId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SystemClock.sleep(mSleepTime);

            synchronized (OneShotSleepTimer.INSTANCE) {
                if (mTaskSessionId == mSessionId) {
                    if (mListener != null) {
                        mListener.onTick(1);
                        mListener.onFinish();
                    }

                    mIsFinished = true;
                }
            }

            return null;
        }
    }
}
