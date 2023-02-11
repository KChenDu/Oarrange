package com.example.oarrange;

import android.content.Context;

import java.util.TimerTask;

abstract class GameTimerTask extends TimerTask {
    Context context;

    public GameTimerTask(Context context) {
        this.context = context;
    }
}
