package com.vincent.tatiana.homeworktrack;

/**
 * Created by tatiana on 10/23/16.
 */

public interface IOnTimerListener {
    /**
     * Calls every tick.
     * @param ticks - ticks count from the start of the timer.
     */
    void onTick(int ticks);

    /**
     * Calls when timer clicks number exceeded.
     */
    void onFinish();
}
