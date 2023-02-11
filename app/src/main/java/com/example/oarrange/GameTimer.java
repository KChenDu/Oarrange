package com.example.oarrange;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

abstract class GameTimerTask extends TimerTask {
    Context context;

    public GameTimerTask(Context context) {
        this.context = context;
    }
}

public class GameTimer extends AppCompatActivity {
    private final Context context;
    private Timer timer;

    public GameTimer(Context context) {
        this.context = context;
        timer = new Timer();
    }

    public void reset(Integer level) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new GameTimerTask(this.context) {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(context, GameOverActivity.class));
            }
        }, (level - 2) * 5000L);
    }
}