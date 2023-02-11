package com.example.oarrange;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GameCountDownTimer extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private TextView textView;

    public GameCountDownTimer(Integer secondsInFuture) {
        textView = findViewById(R.id.textViewCountDown);
        countDownTimer = new CountDownTimer(secondsInFuture * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 60000 + ":" + millisUntilFinished / 1000 % 60);
            }

            @Override
            public void onFinish() {
                finish();
                Intent intent = new Intent();
                intent.setClassName("com.example.oarrange", "GameOverActivity");
            }
        };
        countDownTimer.start();
    }

    public void reset(Integer level) {
        countDownTimer.cancel();
        countDownTimer = new CountDownTimer((level - 2) * 5000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 60000 + ":" + millisUntilFinished / 1000 % 60);
            }

            @Override
            public void onFinish() {
                finish();
                Intent intent = new Intent();
                intent.setClassName("com.example.oarrange", "com.example.oarrange.GameOverActivity");
                startActivity(intent);
            }
        };
        countDownTimer.start();
    }
}